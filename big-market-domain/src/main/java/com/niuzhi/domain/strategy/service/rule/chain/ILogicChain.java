package com.niuzhi.domain.strategy.service.rule.chain;

import com.niuzhi.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @Title: ILogicChain
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain
 * @Date 2026/1/28 15:49
 * @description: 责任链接口
 */
public interface ILogicChain  extends ILogicChainArmory{
    /**
     * 责任链接口
     * @param userId        用户ID
     * @param strategyId    策略ID
     * @return              奖品ID
     */
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);


}
