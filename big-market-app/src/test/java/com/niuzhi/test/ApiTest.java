package com.niuzhi.test;

import com.niuzhi.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        redisService.addToMap("strategy_id_100001", "1", "101");
        redisService.addToMap("strategy_id_100001", "2", "101");
        redisService.addToMap("strategy_id_100001", "3", "101");
        redisService.addToMap("strategy_id_100001", "4", "102");
        redisService.addToMap("strategy_id_100001", "5", "102");
        redisService.addToMap("strategy_id_100001", "6", "102");
        redisService.addToMap("strategy_id_100001", "7", "103");
        redisService.addToMap("strategy_id_100001", "8", "103");
        redisService.addToMap("strategy_id_100001", "9", "104");
        redisService.addToMap("strategy_id_100001", "10", "105");
        log.info("测试结果: {}", redisService.getFromMap("strategy_id_100001", "1"));
    }

}
