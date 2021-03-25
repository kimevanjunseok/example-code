package spring.data.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;

class ListOperationsTest {

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;
}
