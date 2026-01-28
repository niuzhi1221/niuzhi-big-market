package com.niuzhi.domain.strategy.service.rule.tree.factory.engine;

import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @Title: IDecisionTreeEngine
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.tree.factory.engine
 * @Date 2026/1/28 19:19
 * @description: 规则树组合接口（引擎）
 */
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
