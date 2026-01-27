package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.infrastructure.persistent.po.Strategy;
import com.niuzhi.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IStrategyDao
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/26 18:04
 * @description: 策略Dao
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();

    Strategy queryStrategyByStrategyId(Long strategyId);
}
