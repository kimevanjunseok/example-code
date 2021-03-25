package spring.data.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;

class ValueOperationsTest {

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;
}
