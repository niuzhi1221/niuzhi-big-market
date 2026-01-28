package com.niuzhi.domain.strategy.model.vo;

import com.niuzhi.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import com.niuzhi.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: StrategyAwardRuleModel
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.vo
 * @Date 2026/1/28 14:01
 * @description: 抽奖策略规则 规则值对象; 值对象，没有唯一ID，仅限于从数据库查询对象
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyAwardRuleModelVO {
    private String ruleModels;

    public String[] raffleCenterRuleModelList(){
        List<String> ruleModelList = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        for(String ruleModelValue : ruleModelValues){
            if(DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)){
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }
}
