package spring.data.redis;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.HashOperations;

class HashOperationsTest extends RedisTest {

    private static final String HASH_OPERATIONS_KEY = "hashOperationsKey";

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Test
    void HashOperations_Test() {
        // increment
        hashOperations.put(HASH_OPERATIONS_KEY, "count1", "1");
        hashOperations.put(HASH_OPERATIONS_KEY, "count2", "1");
        hashOperations.increment(HASH_OPERATIONS_KEY, "count1", 1);
        hashOperations.increment(HASH_OPERATIONS_KEY, "count2", 2);

        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "count1")).isEqualTo("2");
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "count2")).isEqualTo("3");

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

        assertThat(size).isEqualTo(6);

        // delete
        hashOperations.delete(HASH_OPERATIONS_KEY, "count1", "count2", "tigger1", "tigger2", "tigger3", "notExist");

        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "count1")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "count2")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger1")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger2")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "tigger3")).isNull();
        assertThat(hashOperations.get(HASH_OPERATIONS_KEY, "notExist")).isNull();
    }
}
