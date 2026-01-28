package com.niuzhi.domain.strategy.service.rule.chain;

/**
 * @Title: ILogicChainArmory
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain
 * @Date 2026/1/28 16:26
 * @description: 责任链装配
 */
public interface ILogicChainArmory {
    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
