package com.niuzhi.domain.strategy.repository;

import com.niuzhi.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: IStrategyRepository
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.repository
 * @Date 2026/1/26 19:41
 * @description: 策略仓储接口
 */
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTables(Long strategyId, int rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
