package spring.data.redis;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;

@SpringBootTest(classes = RedisApplication.class)
class ListOperationsTest {

    private static final String LIST_OPERATIONS_KEY = "listOperationsKey";

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;

    @Test
    void ListOperations_Test() {

    }
}
