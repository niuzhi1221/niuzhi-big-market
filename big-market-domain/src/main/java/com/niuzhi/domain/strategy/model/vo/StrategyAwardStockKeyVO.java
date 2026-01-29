package com.niuzhi.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: StrategyAwardStockKeyVO
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.vo
 * @Date 2026/1/29 14:45
 * @description: 策略奖品库存Key标识值对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {

    //  策略ID
    private Long strategyId;
    //  奖品ID
    private Integer awardId;
}
