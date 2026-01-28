package com.niuzhi.domain.strategy.service.rule;

import com.niuzhi.domain.strategy.model.entity.RuleActionEntity;
import com.niuzhi.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @Title: ILogicFilter
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule
 * @Date 2026/1/27 15:34
 * @description: 抽奖规则过滤接口
 */
// <T extends RuleActionEntity.RaffleEntity> 表示 T只能是RaffleEntity的子类
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
