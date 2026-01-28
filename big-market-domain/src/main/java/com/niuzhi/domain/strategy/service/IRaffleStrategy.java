package com.niuzhi.domain.strategy.service;

import com.niuzhi.domain.strategy.model.entity.RaffleAwardEntity;
import com.niuzhi.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @Title: IRaffleStrategy
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service
 * @Date 2026/1/27 15:26
 * @description: 抽奖规则
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity  raffleFactorEntity);
}
