package spring.data.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ZSetOperations;

class ZSetOperationsTest {

    @Resource(name="redisTemplate")
    private ZSetOperations<String, String> zSetOperations;
}
