package com.niuzhi.infrastructure.persistent.repository;

import com.niuzhi.domain.strategy.model.entity.StrategyAwardEntity;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.infrastructure.persistent.dao.IStrategyAwardDao;
import com.niuzhi.infrastructure.persistent.po.StrategyAward;
import com.niuzhi.infrastructure.persistent.redis.IRedisService;
import com.niuzhi.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: StrategyRepository
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.repository
 * @Date 2026/1/26 19:42
 * @description: 策略仓储实现
 */
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyAwardDao strategyAwardDao;
    
    @Resource
    private IRedisService redisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntities = redisService.getValue(cacheKey);
        if(strategyAwardEntities != null && !strategyAwardEntities.isEmpty()){
            return strategyAwardEntities;
        }
        //  从数据库中读取数据
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntities = new ArrayList<>(strategyAwards.size());
        for(StrategyAward strategyAward : strategyAwards){
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                        .strategyId(strategyAward.getStrategyId())
                        .awardId(strategyAward.getAwardId())
                        .awardCount(strategyAward.getAwardCount())
                        .awardCountSurplus(strategyAward.getAwardCountSurplus())
                        .awardRate(strategyAward.getAwardRate())
                        .build();
            strategyAwardEntities.add(strategyAwardEntity);
        }
        redisService.setValue(cacheKey, strategyAwardEntities);
        return strategyAwardEntities;
    }

    @Override
    public void storeStrategyAwardSearchRateTables(Long strategyId, int rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables) {
        // 1.存储抽奖策略范围值
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId, rateRange);
        // 2.存储查找概率表
        RMap<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId);
        cacheRateTable.putAll(shuffleStrategyAwardSearchRateTables);
    }

    @Override
    public int getRateRange(Long strategyId) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId);
    }

    @Override
    public Integer getStrategyAwardAssemble(Long strategyId, int rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId, rateKey);

    }
}
