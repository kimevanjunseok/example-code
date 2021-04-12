package spring.data.redis;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.SetOperations;

class SetOperationsTest extends RedisTest {

    private static final String SET_OPERATIONS_KEY_1 = "setOperationsKey1";
    private static final String SET_OPERATIONS_KEY_2 = "setOperationsKey2";
    private static final String SET_OPERATIONS_KEY_3 = "setOperationsKey3";

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;

    @BeforeEach
    void setUp() {
        setOperations.add(SET_OPERATIONS_KEY_1, "1", "2", "3", "4");
        setOperations.add(SET_OPERATIONS_KEY_2, "3", "4", "5", "6", "7");
    }

    @Test
    void add() {
        setOperations.add(SET_OPERATIONS_KEY_3, "1");
        setOperations.add(SET_OPERATIONS_KEY_3, "2");

        assertThat(setOperations.size(SET_OPERATIONS_KEY_3)).isEqualTo(2);
    }

    @Test
    void members() {
        Set<String> members1 = setOperations.members(SET_OPERATIONS_KEY_1);
        Set<String> members2 = setOperations.members(SET_OPERATIONS_KEY_2);

        assertAll(
                () -> assertThat(members1).hasSize(4),
                () -> assertThat(members2).hasSize(5)
        );
    }

    @Test
    void intersect() {
        Set<String> intersect = setOperations.intersect(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);

        assertThat(intersect).hasSize(2);
    }

    @Test
    void union() {
        Set<String> union = setOperations.union(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);

        assertThat(union).hasSize(7);
    }

    @Test
    void difference() {
        Set<String> difference1 = setOperations.difference(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);
        Set<String> difference2 = setOperations.difference(SET_OPERATIONS_KEY_2, SET_OPERATIONS_KEY_1);

        assertThat(difference1).hasSize(2);
        assertThat(difference2).hasSize(3);
    }

    @Test
    void remove() {
        setOperations.remove(SET_OPERATIONS_KEY_2, "3");
        setOperations.remove(SET_OPERATIONS_KEY_2, "3", "4", "5");

        assertAll(
                () -> assertThat(setOperations.size(SET_OPERATIONS_KEY_1)).isEqualTo(3),
                () -> assertThat(setOperations.size(SET_OPERATIONS_KEY_2)).isEqualTo(2)
        );
    }

    @Test
    void pop() {
        List<String> pops = setOperations.pop(SET_OPERATIONS_KEY_1, 4);
        String pop = setOperations.pop(SET_OPERATIONS_KEY_2);

        assertAll(
                () -> assertThat(pops).hasSize(4),
                () -> assertThat(pop).isNotNull()
        );
    }
}
