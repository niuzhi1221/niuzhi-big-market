package com.niuzhi.domain.strategy.service.rule.tree.impl;

import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.niuzhi.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Title: RuleStockLogicTreeNode
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.tree.impl
 * @Date 2026/1/28 19:16
 * @description:
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity loigc(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckTypeVO(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
