package spring.data.redis;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;

@SpringBootTest(classes = RedisApplication.class)
class HashOperationsTest {

    private static final String HASH_OPERATIONS_KEY = "hashOperationsKey";

    @Resource(name = "redisTemplate")
    private HashOperations<String, Object, Object> hashOperations;

    @Test
    void HashOperations_Test() {
        // put
        hashOperations.put(HASH_OPERATIONS_KEY, "tigger1", "개발자");
        hashOperations.put(HASH_OPERATIONS_KEY, "tigger2", "취준생");
        hashOperations.put(HASH_OPERATIONS_KEY, "tigger3", "백수");

        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger1")).isEqualTo("개발자");
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger2")).isEqualTo("취준생");
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger3")).isEqualTo("백수");

        // putIfAbsent
        Boolean tigger1 = hashOperations.putIfAbsent(HASH_OPERATIONS_KEY, "tigger1", "요리사");
        Boolean notExist = hashOperations.putIfAbsent(HASH_OPERATIONS_KEY, "notExist", "개발자");

        assertThat(tigger1).isFalse();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger1")).isEqualTo("개발자");
        assertThat(notExist).isTrue();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "notExist")).isEqualTo("개발자");

        // size
        Long size = hashOperations.size(HASH_OPERATIONS_KEY);

        assertThat(size).isEqualTo(4);

        // delete
        hashOperations.delete(HASH_OPERATIONS_KEY, "tigger1", "tigger2", "tigger3", "notExist");

        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger1")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger2")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger3")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "notExist")).isNull();
    }
}
