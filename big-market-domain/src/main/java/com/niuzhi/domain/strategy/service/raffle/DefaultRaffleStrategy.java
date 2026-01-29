package com.niuzhi.domain.strategy.service.raffle;

import com.niuzhi.domain.strategy.model.vo.RuleTreeVO;
import com.niuzhi.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.niuzhi.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.AbstractRaffleStrategy;
import com.niuzhi.domain.strategy.service.armory.IStrategyDispatch;
import com.niuzhi.domain.strategy.service.rule.chain.ILogicChain;
import com.niuzhi.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.niuzhi.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Title: DefaultRaffleStrategy
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.raffle
 * @Date 2026/1/27 16:45
 * @description: 默认的抽奖策略实现
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {


    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, strategyDispatch, defaultChainFactory, defaultTreeFactory);
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        //  如果没有配置其他规则，则不用进行过滤，直接返回奖品StrategyAwardVO
        if(null ==  strategyAwardRuleModelVO){
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        //  如果发现规则树的信息没有被配置,则直接抛出异常
        if(null ==  ruleTreeVO){
            throw new RuntimeException("存在抽奖策略配置的规则模型 Key，未在库表 rule_tree、rule_tree_node、rule_tree_line 配置对应的规则树信息 " + strategyAwardRuleModelVO.getRuleModels());
        }
        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return treeEngine.process(userId, strategyId, awardId);

    }

    @Override
    public StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException {
        return repository.takeQueueValue();
    }

    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        repository.updateStrategyAwardStock(strategyId, awardId);
    }
}
