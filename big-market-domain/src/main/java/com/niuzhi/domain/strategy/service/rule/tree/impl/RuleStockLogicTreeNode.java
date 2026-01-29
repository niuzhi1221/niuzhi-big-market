package com.niuzhi.domain.strategy.service.rule.tree.impl;

import com.niuzhi.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.niuzhi.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import com.niuzhi.domain.strategy.service.armory.IStrategyDispatch;
import com.niuzhi.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.niuzhi.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: RuleStockLogicTreeNode
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.tree.impl
 * @Date 2026/1/28 19:16
 * @description: 库存扣减节点
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DefaultTreeFactory.TreeActionEntity loigc(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-库存扣减 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        //  扣减库存
        Boolean status = strategyDispatch.subtractionAwardStock(strategyId, awardId);
        //  status true 扣减成功
        if(status){
            //  写入延迟队列,延迟消费更新数据库记录 【在trigger的job: UpdateAwardStockJob下消费队列, 更新数据库记录】
            strategyRepository.awardStockConsumeSendQueue(StrategyAwardStockKeyVO.builder()
                    .strategyId(strategyId)
                    .awardId(awardId)
                    .build());

            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckTypeVO(RuleLogicCheckTypeVO.TAKE_OVER)
                    .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                            .awardId(awardId)
                            .awardRuleValue("")
                            .build())
                    .build();
        }

        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckTypeVO(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
