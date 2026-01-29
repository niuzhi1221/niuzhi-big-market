package com.niuzhi.test.infrastructure;

import com.alibaba.fastjson.JSON;
import com.niuzhi.domain.strategy.model.vo.RuleTreeVO;
import com.niuzhi.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Title: StrategyRepositoryTest
 * @Author niuzhi
 * @Package com.niuzhi.test.infrastructure
 * @Date 2026/1/29 11:58
 * @description: 仓储数据查询
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyRepositoryTest {

    @Resource
    private IStrategyRepository strategyRepository;

    @Test
    public void queryRuleTreeVOByTreeId(){
        RuleTreeVO ruleTreeVO = strategyRepository.queryRuleTreeVOByTreeId("tree_lock");
        log.info("测试结果: {}", JSON.toJSONString(ruleTreeVO));
    }
}
