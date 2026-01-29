package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.domain.strategy.model.entity.StrategyAwardEntity;
import com.niuzhi.infrastructure.persistent.po.Award;
import com.niuzhi.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IStrategyAwardDao
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/26 18:05
 * @description: 抽奖策略明细Dao
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

    String queryStrategyAwardRuleModels(StrategyAward strategyAward);

    void updateStrategyAwardStock(StrategyAward strategyAward);
}
