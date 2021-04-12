package spring.data.redis;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ZSetOperations;

class ZSetOperationsTest extends RedisTest {

    private static final String Z_SET_OPERATIONS_KEY = "zSetOperationsKey";

    @Resource(name="redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    @BeforeEach
    void setUp() {
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "A", 10);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "B", 30);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "C", 40);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "D", 10);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "F", 20);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "F", 50);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "G", 10);
        zSetOperations.add(Z_SET_OPERATIONS_KEY, "G", 60);
    }

    @Test
    void score() {
        assertAll(
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "A")).isEqualTo(10),
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "B")).isEqualTo(30),
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "C")).isEqualTo(40),
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "D")).isEqualTo(10),
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "F")).isEqualTo(50),
                () -> assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "G")).isEqualTo(60)
        );
    }

    @Test
    void incrementScore() {
        zSetOperations.incrementScore(Z_SET_OPERATIONS_KEY, "G", 1);

        assertThat(zSetOperations.score(Z_SET_OPERATIONS_KEY, "G")).isEqualTo(61);
    }

    @Test
    void size() {
        assertThat(zSetOperations.size(Z_SET_OPERATIONS_KEY)).isEqualTo(6);
    }

    @Test
    void count() {
        assertThat(zSetOperations.count(Z_SET_OPERATIONS_KEY, 10, 20)).isEqualTo(2);
    }

    @Test
    void rangeByScore() {
        Set<String> sets = zSetOperations.rangeByScore(Z_SET_OPERATIONS_KEY, 10, 20);

        assertAll(
                () -> assertThat(sets).hasSize(2),
                () -> assertThat(sets).contains("A", "D")
        );
    }

    @Test
    void rank() {
        assertAll(
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "A")).isEqualTo(0),
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "B")).isEqualTo(2),
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "C")).isEqualTo(3),
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "D")).isEqualTo(1),
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "F")).isEqualTo(4),
                () -> assertThat(zSetOperations.rank(Z_SET_OPERATIONS_KEY, "G")).isEqualTo(5)
        );
    }

    @Test
    void reverseRank() {
        assertAll(
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "A")).isEqualTo(5),
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "B")).isEqualTo(3),
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "C")).isEqualTo(2),
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "D")).isEqualTo(4),
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "F")).isEqualTo(1),
                () -> assertThat(zSetOperations.reverseRank(Z_SET_OPERATIONS_KEY, "G")).isEqualTo(0)
        );
    }
}
