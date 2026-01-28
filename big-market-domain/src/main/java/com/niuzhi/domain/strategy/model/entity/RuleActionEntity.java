package com.niuzhi.domain.strategy.model.entity;

import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @Title: RuleActionEntity
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.entity
 * @Date 2026/1/27 15:36
 * @description: 规则动作实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;

    static public class RaffleEntity{

    }

    //  抽奖之前过滤，对应rule_weight, rule_blacklist
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity{
        /**
         * 策略ID
         */
        private Long strategyId;

        /**
         * 权重值key:用于抽奖时可以选择权重抽奖
         */
        private String ruleWeightValueKey;

        /**
         * 奖品ID: 是Blcak_list抽奖，所以要直接返回奖品ID
         */
        private Integer awardId;

    }

    //  抽奖之中过滤, 对应rule_lock
    static public class RaffleCenterEntity extends RaffleEntity{

    }

    //  抽奖之后过滤, 对应rule_luck_award
    static public class RaffleAfterEntity extends RaffleEntity{

    }
}
