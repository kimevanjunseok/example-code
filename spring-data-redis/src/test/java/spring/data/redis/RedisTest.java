package spring.data.redis;

import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = RedisApplication.class)
class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @AfterEach
    void teardown() {
        Set<String> keys = redisTemplate.keys("*");
        for (final String key : Objects.requireNonNull(keys)) {
            redisTemplate.delete(key);
        }
    }
}
