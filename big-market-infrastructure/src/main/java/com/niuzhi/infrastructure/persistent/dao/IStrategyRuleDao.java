package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.infrastructure.persistent.po.Strategy;
import com.niuzhi.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IStrategyRuleDao
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/26 18:05
 * @description: 策略规则Dao
 */
@Mapper
public interface IStrategyRuleDao {

    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);
}
