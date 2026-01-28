package com.niuzhi.domain.strategy.service.rule.factory;

import com.niuzhi.domain.strategy.model.entity.RuleActionEntity;
import com.niuzhi.domain.strategy.service.annotation.LogicStrategy;
import com.niuzhi.domain.strategy.service.rule.ILogicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: DefaultLogicFactory
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.factory
 * @Date 2026/1/27 15:54
 * @description: 规则工厂
 */
@Service
public class DefaultLogicFactory {

    //  key:规则标识(如"rule_weight")
    //  value:具体规则实现类(实现了ILogicFilter接口)
    //  使用ConcurrentHashMap是为了线程安全
    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    //  遍历所有实现了ILogicFilter接口的bean(也就是参数logicFilters)
    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        logicFilters.forEach(logic -> {
            /**
             * 读取注解中的logicMode，把logicMode.code作为key，把规则实现类实例注册到Map
             */
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (null != strategy) {
                logicFilterMap.put(strategy.logicMode().getCode(), logic);
            }
        });
    }

    /**
     * 将内部保存的规则Map暴露给业务层
     * @return
     * @param <T>
     */
    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WEIGHT("rule_weight","【抽奖前规则】根据抽奖权重返回可抽奖范围KEY", "before"),
        RULE_BLACKLIST("rule_blacklist","【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回", "before"),
        RULE_LOCK("rule_lock","【抽奖中规则】抽奖N次后，对应奖品可解锁抽奖", "center"),
        RULE_LUCK_AWARD("rule_luck_award","【抽奖后规则】幸运奖兜底", "after"),

        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isCenter(String code){
            return "center".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfter(String code){
            return "after".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }
    }

}