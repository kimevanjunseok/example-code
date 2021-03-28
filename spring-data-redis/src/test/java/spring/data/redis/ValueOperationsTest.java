package spring.data.redis;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ValueOperations;

class ValueOperationsTest extends RedisTest {

    private static final String VALUE_OPERATIONS_KEY = "valueOperationsKey";

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Test
    void ValueOperations_Test() {
        // append
        valueOperations.append(VALUE_OPERATIONS_KEY, "Hello");
        valueOperations.append(VALUE_OPERATIONS_KEY, "World");
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("HelloWorld");

        // set
        valueOperations.set(VALUE_OPERATIONS_KEY, "1");
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("1");

        // increment
        valueOperations.increment(VALUE_OPERATIONS_KEY);
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("2");

        valueOperations.increment(VALUE_OPERATIONS_KEY, 3);
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("5");

        // decrement
        valueOperations.decrement(VALUE_OPERATIONS_KEY);
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("4");

        valueOperations.decrement(VALUE_OPERATIONS_KEY, 3);
        assertThat(valueOperations.get(VALUE_OPERATIONS_KEY)).isEqualTo("1");

        // size
        assertThat(valueOperations.size(VALUE_OPERATIONS_KEY)).isEqualTo(1);
    }
}
