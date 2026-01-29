package com.niuzhi.domain.strategy.service.rule.chain.impl;

import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.armory.IStrategyDispatch;
import com.niuzhi.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.niuzhi.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.niuzhi.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Title: RuleWeightLogicChain
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain.impl
 * @Date 2026/1/28 15:56
 * @description: 权重
 */
@Slf4j
@Component("rule_weight")
public class RuleWeightLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Resource
    protected IStrategyDispatch strategyDispatch;

    private Long userScore = 0L;

    /**
     * 权重规则过滤；
     *     1. 权重规则格式；4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     *     2. 解析数据格式；判断哪个范围符合用户的特定抽奖范围
     */
    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-权重开始 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());

        // 1.根据用户ID查询用户抽奖消耗的积分值,本章节先写死为固定的值，后续要从数据库中查询
        // analyticalValueGroup中的值举例<4000, 4000:102,103,104,105>
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if(analyticalValueGroup == null || analyticalValueGroup.isEmpty()) return null;

        // 2.转换Keys值，并默认排序
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);

        // 3.找出最小值
        Long nextValue = analyticalSortedKeys.stream()
                .sorted(Comparator.reverseOrder())
                .filter(analyticalKeyValue -> userScore >= analyticalKeyValue)
                .findFirst()
                .orElse(null);

        if(nextValue !=null){
            Integer awardId = strategyDispatch.getRandomAwardId(strategyId, analyticalValueGroup.get(nextValue));
            log.info("抽奖责任链-权重接管 userId:{} strategyId:{} ruleModel:{} awardId:{}", userId, strategyId, ruleModel(), awardId);
            return DefaultChainFactory.StrategyAwardVO.builder()
                    .awardId(awardId)
                    .logicModel(ruleModel())
                    .build();
        }

        log.info("抽奖责任链-权重放行 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_WEIGHT.getCode();
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
