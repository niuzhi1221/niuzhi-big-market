package com.niuzhi.domain.strategy.model.entity;

import com.niuzhi.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: StrategyRuleEntity
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.entity
 * @Date 2026/1/27 14:23
 * @description: 策略规则实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRuleEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID【规则类型为策略，则不需要奖品ID】 */
    private Integer awardId;
    /** 抽象规则类型；1-策略规则、2-奖品规则 */
    private Integer ruleType;
    /** 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleModel;
    /** 抽奖规则比值 */
    private String ruleValue;
    /** 抽奖规则描述 */
    private String ruleDesc;

    public Map<String, List<Integer>> getRuleWeightValues(){
        /** 获取权重值
         * 输入示例: 4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
         */
        if(!"rule_weight".equals(ruleModel)) return null;
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for(String ruleValueGroup : ruleValueGroups){
            //  检查输入是否为空
            if(ruleValueGroup == null || ruleValueGroup.isEmpty()) return resultMap;
            //  分割字符串以获取key 和 value
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if(parts.length !=2){
                throw new IllegalArgumentException("rule_weight invalid input format" + ruleValueGroup);
            }
            //  解析值
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for(String valueString : valueStrings){
                values.add(Integer.parseInt(valueString));
            }
            // 将key 和 value放入map中
            resultMap.put(ruleValueGroup, values);
        }
        return resultMap;
    }
}
