package com.niuzhi.domain.strategy.service.rule.chain.impl;

import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.niuzhi.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: BlackListLogicChain
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain
 * @Date 2026/1/28 15:56
 * @description:
 */
@Slf4j
@Component("rule_blacklist")
public class BlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.valueOf(splitRuleValue[0]);
        // 过滤其他规则
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for(String userIdBlackId : userBlackIds){
            if(userId.equals(userIdBlackId)){
                log.info("抽奖责任链-黑名单接管 userId:{} strategyId:{} ruleModel:{} awardId:{}", userId, strategyId, ruleModel(), awardId);
                return awardId;
            }
        }
        log.info("抽奖责任链-黑名单放行 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return "rule_blacklist";
    }
}
