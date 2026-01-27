package com.niuzhi.test.domain;

import com.niuzhi.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Title: StrategyArmoryTest
 * @Author niuzhi
 * @Package com.niuzhi.test.domain
 * @Date 2026/1/26 20:46
 * @description: 对策略装配进行测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Test
    public void test_strategyArmory(){
        strategyArmory.assembleLotteryStrategy(100002L);
    }

    @Test
    public void test_getAssembleRandomVal(){
        log.info("测试结果: {} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果: {} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果: {} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
    }
}
