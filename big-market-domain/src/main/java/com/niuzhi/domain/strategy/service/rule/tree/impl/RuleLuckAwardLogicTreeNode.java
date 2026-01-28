package com.niuzhi.domain.strategy.service.rule.tree.impl;

import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.niuzhi.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
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
    public DefaultTreeFactory.TreeActionEntity loigc(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckTypeVO(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardData(DefaultTreeFactory.StrategyAwardData.builder()
                        .awardId(awardId)
                        .awardRuleValue("1, 100")
                        .build())
                .build();
    }
}
