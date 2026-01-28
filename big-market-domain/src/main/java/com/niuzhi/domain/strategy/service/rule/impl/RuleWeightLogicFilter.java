package com.niuzhi.domain.strategy.service.rule.impl;

import com.niuzhi.domain.strategy.model.entity.RuleActionEntity;
import com.niuzhi.domain.strategy.model.entity.RuleMatterEntity;
import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.annotation.LogicStrategy;
import com.niuzhi.domain.strategy.service.rule.ILogicFilter;
import com.niuzhi.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.niuzhi.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Title: RuleWeightLogicFilter
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.impl
 * @Date 2026/1/27 16:15
 * @description:
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_WEIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository repository;

    private Long userScore = 4500L;

    /**
     * 权重规则过滤；
     *     1. 权重规则格式；4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     *     2. 解析数据格式；判断哪个范围符合用户的特定抽奖范围
     *
     * @param ruleMatterEntity 规则物料实体对象
     * @return 规则过滤结果
     */
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-权重范围 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        String userId = ruleMatterEntity.getUserId();
        Long strategyId = ruleMatterEntity.getStrategyId();
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(),  ruleMatterEntity.getRuleModel());

        // 1.根据用户ID查询用户抽奖消耗的积分值,本章节先写死为固定的值，后续要从数据库中查询
        // analyticalValueGroup中的值举例<4000, 4000:102,103,104,105>
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if(analyticalValueGroup == null || analyticalValueGroup.isEmpty()){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 2.转换Keys值，并默认排序
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);

        // 3.找出最小符合的值,比如4500积分应该找到4000
        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore>= key)
                .findFirst()
                .orElse(null);  //  orElse的作用是找到则返回,找不到则返回null
        if(nextValue != null){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .data(
                            RuleActionEntity.RaffleBeforeEntity.builder()
                                    .strategyId(strategyId)
                                    .ruleWeightValueKey(analyticalValueGroup.get(nextValue))
                                    .build()
                    )
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

    private Map<Long, String> getAnalyticalValue(String ruleValue){
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for(String ruleValueGroup : ruleValueGroups){
            //  检查输入是否为空
            if(ruleValueGroup == null || ruleValueGroup.isEmpty()){
                return ruleValueMap;
            }
            //  分割字符串以获取键和值
            String[] parts=ruleValueGroup.split(Constants.COLON);
            if(parts.length != 2){
                throw new IllegalArgumentException("rule_weight invalid input format" + ruleValueGroup);
            }
            ruleValueMap.put(Long.parseLong(parts[0]), ruleValueGroup);
        }
        return ruleValueMap;
    }
}
