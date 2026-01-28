package com.niuzhi.domain.strategy.service.rule.chain.impl;

import com.niuzhi.domain.strategy.service.armory.IStrategyDispatch;
import com.niuzhi.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: DefaultLogicChain
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain.impl
 * @Date 2026/1/28 15:57
 * @description: 兜底
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId:{} strategyId:{} ruleModel:{} awardId:{}", userId, strategyId, ruleModel(), awardId);
        return 0;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }
}
