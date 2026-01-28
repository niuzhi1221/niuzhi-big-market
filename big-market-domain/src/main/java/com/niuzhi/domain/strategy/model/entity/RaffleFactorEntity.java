package com.niuzhi.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: RaffleFactorEntity
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.entity
 * @Date 2026/1/27 15:26
 * @description: 抽奖因子
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RaffleFactorEntity {

    private String userId;
    private Long strategyId;
}
