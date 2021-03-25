package spring.data.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.SetOperations;

class SetOperationsTest {

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;
}
