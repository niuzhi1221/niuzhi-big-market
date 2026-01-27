package com.niuzhi.domain.strategy.service.armory;

/**
 * @Title: IStrategyDispatch
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.armory
 * @Date 2026/1/27 13:56
 * @description: 策略抽奖的调度
 */
public interface IStrategyDispatch {
    /**
     * 获取抽奖策略装配的随机结果
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
