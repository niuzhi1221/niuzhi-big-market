package com.niuzhi.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: StrategyAwardEntity
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.entity
 * @Date 2026/1/26 19:44
 * @description: 策略奖品实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyAwardEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 奖品库存总量 */
    private Integer awardCount;
    /** 奖品库存剩余 */
    private String awardCountSurplus;
    /** 奖品中奖概率 */
    private BigDecimal awardRate;
}
