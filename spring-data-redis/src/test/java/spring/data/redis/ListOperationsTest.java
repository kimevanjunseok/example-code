package spring.data.redis;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ListOperations;

class ListOperationsTest extends RedisTest {

    private static final String LIST_OPERATIONS_KEY = "listOperationsKey";

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;

    @Test
    void ListOperations_Test() {
        // leftPush
        listOperations.leftPush(LIST_OPERATIONS_KEY, "o");
        listOperations.leftPushAll(LIST_OPERATIONS_KEY, "l", "l");
        listOperations.leftPushAll(LIST_OPERATIONS_KEY, Arrays.asList("e", "H"));

        // rightPush
        listOperations.rightPush(LIST_OPERATIONS_KEY, " ");
        listOperations.rightPush(LIST_OPERATIONS_KEY, "W");
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, "o", "r");
        listOperations.rightPushAll(LIST_OPERATIONS_KEY, Arrays.asList("l", "d"));

        // index
        assertThat(listOperations.index(LIST_OPERATIONS_KEY, 0)).isEqualTo("H");
        assertThat(listOperations.index(LIST_OPERATIONS_KEY, 10)).isEqualTo("d");

        // range
        assertThat(listOperations.range(LIST_OPERATIONS_KEY, 0, 10)).isEqualTo(
                Arrays.asList("H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d")
        );

        // remove
        listOperations.remove(LIST_OPERATIONS_KEY, 1, " ");

        assertThat(listOperations.range(LIST_OPERATIONS_KEY, 0, 9)).isEqualTo(
                Arrays.asList("H", "e", "l", "l", "o", "W", "o", "r", "l", "d")
        );

        // leftPop
        String leftPush1 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush2 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush3 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush4 = listOperations.leftPop(LIST_OPERATIONS_KEY);
        String leftPush5 = listOperations.leftPop(LIST_OPERATIONS_KEY);

        assertThat(leftPush1).isEqualTo("H");
        assertThat(leftPush2).isEqualTo("e");
        assertThat(leftPush3).isEqualTo("l");
        assertThat(leftPush4).isEqualTo("l");
        assertThat(leftPush5).isEqualTo("o");

        // rightPop
        String rightPop1 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop2 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop3 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop4 = listOperations.rightPop(LIST_OPERATIONS_KEY);
        String rightPop5 = listOperations.rightPop(LIST_OPERATIONS_KEY);

        assertThat(rightPop1).isEqualTo("d");
        assertThat(rightPop2).isEqualTo("l");
        assertThat(rightPop3).isEqualTo("r");
        assertThat(rightPop4).isEqualTo("o");
        assertThat(rightPop5).isEqualTo("W");
    }
}
