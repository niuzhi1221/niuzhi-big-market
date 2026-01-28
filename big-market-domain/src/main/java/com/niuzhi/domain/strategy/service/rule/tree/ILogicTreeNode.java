package com.niuzhi.domain.strategy.service.rule.tree;

import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @Title: ILogicTreeNode
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.tree
 * @Date 2026/1/28 19:04
 * @description: 规则树接口
 */
public interface ILogicTreeNode {
    DefaultTreeFactory.TreeActionEntity loigc(String userId, Long strategyId, Integer awardId);
}
