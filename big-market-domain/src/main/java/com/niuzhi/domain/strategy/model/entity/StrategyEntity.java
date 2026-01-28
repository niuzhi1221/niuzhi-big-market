package com.niuzhi.domain.strategy.model.entity;

import com.niuzhi.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Title: StrategyEntity
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.entity
 * @Date 2026/1/27 14:06
 * @description: 策略实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyEntity {

    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 抽奖规则模型 */
    private String ruleModels;

    public String[] ruleModels(){
        if(StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.SPLIT);
    }

    public String getRuleWeight(){
        String[] ruleModels = this.ruleModels();
        if(null == ruleModels) return null;
        for(String ruleModel : ruleModels){
            if("rule_weight".equals(ruleModel)) return ruleModel;
        }
        return null;
    }
}
