package com.niuzhi.domain.strategy.service.armory;

import com.niuzhi.domain.strategy.model.entity.StrategyAwardEntity;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: StrategyArmory
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.armory
 * @Date 2026/1/26 19:38
 * @description: 策略装配库的实现类
 */

@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory{

    @Resource
    private IStrategyRepository repository;

    @Override
    public void assembleLotteryStrategy(Long strategyId) {
        //  1.查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2.获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 3.获取概率值总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4.用"概率值总和 % 最小概率值"获取概率范围(百分位、千分位or万分位)
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 5.生成
        ArrayList<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward :  strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();

            //  计算出每个概率值需要存放到查找表的数量，循环填充
            for(int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        // 6.乱序
        Collections.shuffle(strategyAwardSearchRateTables);

        // 7.得到乱序后的查找表
        HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables = new HashMap<>();
        for(int i=0; i<strategyAwardSearchRateTables.size(); i++){
            shuffleStrategyAwardSearchRateTables.put(i, strategyAwardSearchRateTables.get(i));
        }

        // 8.存储到Redis
        repository.storeStrategyAwardSearchRateTables(strategyId, shuffleStrategyAwardSearchRateTables.size(), shuffleStrategyAwardSearchRateTables);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
