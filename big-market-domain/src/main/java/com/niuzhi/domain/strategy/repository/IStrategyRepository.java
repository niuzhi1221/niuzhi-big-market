package com.niuzhi.domain.strategy.repository;

import com.niuzhi.domain.strategy.model.entity.StrategyAwardEntity;
import com.niuzhi.domain.strategy.model.entity.StrategyEntity;
import com.niuzhi.domain.strategy.model.entity.StrategyRuleEntity;
import com.niuzhi.domain.strategy.model.vo.RuleTreeVO;
import com.niuzhi.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.niuzhi.domain.strategy.model.vo.StrategyAwardStockKeyVO;

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

    void storeStrategyAwardSearchRateTables(String key, int rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    int getRateRange(String  key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     * @param cacheKey      key
     * @param awardCount    库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 缓存key, decr 方式扣减库存
     * @param cacheKey  缓存key
     * @return  扣减结果
     */
    Boolean substractionAwardStock(String cacheKey);

    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    StrategyAwardStockKeyVO takeQueueValue();

    void updateStrategyAwardStock(Long strategyId, Integer awardId);
}
