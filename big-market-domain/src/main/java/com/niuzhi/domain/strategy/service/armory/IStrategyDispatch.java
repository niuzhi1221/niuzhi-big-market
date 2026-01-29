package com.niuzhi.domain.strategy.service.armory;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     * @param strategyId    策略ID
     * @param awardId       奖品ID
     * @return
     */
    Boolean subtractionAwardStock(Long strategyId, Integer awardId);
}
