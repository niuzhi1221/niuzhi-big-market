package com.niuzhi.domain.strategy.service.armory;

/**
 * @Title: IStrategyArmory
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.armory
 * @Date 2026/1/26 19:37
 * @description: 策略装配库（兵工厂），负责初始化策略计算
 */
public interface IStrategyArmory {

    /**
     * 装配抽奖策略配置
     * @param strategyId
     */
    void assembleLotteryStrategy(Long strategyId);

    /**
     * 获取抽奖策略装配的随机结果
     * @param strategyId
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId);
}
