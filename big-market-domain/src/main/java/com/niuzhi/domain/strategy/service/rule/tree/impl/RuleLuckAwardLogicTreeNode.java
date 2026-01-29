package com.niuzhi.domain.strategy.service.rule.tree.impl;

import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.niuzhi.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.niuzhi.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

/**
 * @Title: RuleLuckAwardLogicTreeNode
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.tree.impl
 * @Date 2026/1/28 19:07
 * @description: 兜底奖励
 */
@Slf4j
@Component("rule_luck_award")   //  在Spring中会自动注入 key = BeanName(也就是"rule_luck_award")  value = Bean实例(也就是RuleLuckAwardLogicTreeNode)
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity loigc(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-兜底奖品 userId:{} strategyId:{} awardId:{} ruleValue:{}", userId, strategyId, awardId, ruleValue);
        String[] split = ruleValue.split(Constants.COLON);
        if (split.length == 0) {
            log.error("规则过滤-兜底奖品，兜底奖品未配置告警 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            throw new RuntimeException("兜底奖品未配置 " + ruleValue);
        }
        // 兜底奖励配置
        Integer luckAwardId = Integer.valueOf(split[0]);
        String awardRuleValue = split.length > 1 ? split[1] : "";
        // 返回兜底奖品
        log.info("规则过滤-兜底奖品 userId:{} strategyId:{} awardId:{} awardRuleValue:{}", userId, strategyId, luckAwardId, awardRuleValue);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckTypeVO(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(luckAwardId)
                        .awardRuleValue(awardRuleValue)
                        .build())
                .build();
    }
}
