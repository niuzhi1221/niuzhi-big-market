package com.niuzhi.domain.strategy.service.rule.chain.factory;

import com.niuzhi.domain.strategy.model.entity.StrategyEntity;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.rule.chain.ILogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: DefaultChainFactory
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain.factory
 * @Date 2026/1/28 16:17
 * @description:
 */
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    private final IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    public ILogicChain openLogicChain(Long strategyId){
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        if(null == ruleModels || 0 == ruleModels.length){
            return logicChainGroup.get("default");
        }
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain curr = logicChain;
        for(int i=1;i<ruleModels.length;i++){
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            curr = curr.appendNext(nextChain);
        }
        curr.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }
}
