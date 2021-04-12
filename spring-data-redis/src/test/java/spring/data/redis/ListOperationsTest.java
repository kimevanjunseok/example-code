package spring.data.redis;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ListOperations;

class ListOperationsTest extends RedisTest {

    private static final String LIST_OPERATIONS_KEY = "listOperationsKey";

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;

    @Test
    void leftPush() {
        listOperations.leftPush(LIST_OPERATIONS_KEY, "o");
        listOperations.leftPushAll(LIST_OPERATIONS_KEY, "l", "l");
        listOperations.leftPushAll(LIST_OPERATIONS_KEY, Arrays.asList("e", "H"));

        assertAll(
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 0)).isEqualTo("H"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 1)).isEqualTo("e"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 2)).isEqualTo("l"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 3)).isEqualTo("l"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 4)).isEqualTo("o")
        );
    }

    @Test
    void rightPush() {
        listOperations.rightPush(LIST_OPERATIONS_KEY, "W");
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, "o", "r");
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("l", "d"));

        assertAll(
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 0)).isEqualTo("W"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 1)).isEqualTo("o"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 2)).isEqualTo("r"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 3)).isEqualTo("l"),
                () -> assertThat(listOperations.index(LIST_OPERATIONS_KEY, 4)).isEqualTo("d")
        );
    }

    @Test
    void range() {
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d"));

        assertThat(listOperations.range(LIST_OPERATIONS_KEY, 0, 10)).isEqualTo(
                Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d")
        );
    }

    @Test
    void remove() {
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d"));
        listOperations.remove(LIST_OPERATIONS_KEY, 1, " ");

        assertThat(listOperations.range(LIST_OPERATIONS_KEY, 0, 9)).isEqualTo(
                Arrays.asList("H", "e", "l", "l", "o", "W", "o", "r", "l", "d")
        );
    }

    @Test
    void leftPop() {
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d"));
        String leftPush1 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush2 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush3 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush4 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush5 = listOperations.leftPop(LIST_OPERATIONS_KEY);

        assertAll(
                () -> assertThat(leftPush1).isEqualTo("H"),
                () -> assertThat(leftPush2).isEqualTo("e"),
                () -> assertThat(leftPush3).isEqualTo("l"),
                () -> assertThat(leftPush4).isEqualTo("l"),
                () -> assertThat(leftPush5).isEqualTo("o")
        );
    }

    @Test
    void rightPop() {
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d"));
        String rightPop1 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop2 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop3 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop4 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop5 = listOperations.rightPop(LIST_OPERATIONS_KEY);

        assertAll(
                () -> assertThat(rightPop1).isEqualTo("d"),
                () -> assertThat(rightPop2).isEqualTo("l"),
                () -> assertThat(rightPop3).isEqualTo("r"),
                () -> assertThat(rightPop4).isEqualTo("o"),
                () -> assertThat(rightPop5).isEqualTo("W")
        );
    }
}
