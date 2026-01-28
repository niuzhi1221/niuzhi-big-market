package com.niuzhi.domain.strategy.service.rule.chain;

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
    Integer logic(String userId, Long strategyId);


}
