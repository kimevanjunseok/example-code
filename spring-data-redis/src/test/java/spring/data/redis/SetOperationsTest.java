package spring.data.redis;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;

@SpringBootTest(classes = RedisApplication.class)
class SetOperationsTest {

    private static final String SET_OPERATIONS_KEY_1 = "setOperationsKey1";
    private static final String SET_OPERATIONS_KEY_2 = "setOperationsKey2";

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;

    @Test
    void SetOperations_Test() {
        // add
        setOperations.add(SET_OPERATIONS_KEY_1, "1");
        setOperations.add(SET_OPERATIONS_KEY_1, "2");
        setOperations.add(SET_OPERATIONS_KEY_1, "3");
        setOperations.add(SET_OPERATIONS_KEY_1, "4");
        setOperations.add(SET_OPERATIONS_KEY_2, "3", "4", "5", "6", "7");

        // members
        Set<String> members1 = setOperations.members(SET_OPERATIONS_KEY_1);
        Set<String> members2 = setOperations.members(SET_OPERATIONS_KEY_2);

        assertThat(members1).hasSize(4);
        assertThat(members2).hasSize(5);

        // size
        assertThat(setOperations.size(SET_OPERATIONS_KEY_1)).isEqualTo(4);
        assertThat(setOperations.size(SET_OPERATIONS_KEY_2)).isEqualTo(5);

        // intersect
        Set<String> intersect = setOperations.intersect(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);
        assertThat(intersect).hasSize(2);

        // union
        Set<String> union = setOperations.union(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);
        assertThat(union).hasSize(7);

        // difference
        Set<String> difference1 = setOperations.difference(SET_OPERATIONS_KEY_1, SET_OPERATIONS_KEY_2);
        assertThat(difference1).hasSize(2);

        Set<String> difference2 = setOperations.difference(SET_OPERATIONS_KEY_2, SET_OPERATIONS_KEY_1);
        assertThat(difference2).hasSize(3);

        // remove
        setOperations.remove(SET_OPERATIONS_KEY_2, "7");
        setOperations.remove(SET_OPERATIONS_KEY_2, "3", "4", "5");

        // pop
        List<String> pops = setOperations.pop(SET_OPERATIONS_KEY_1, 4);
        String pop = setOperations.pop(SET_OPERATIONS_KEY_2);

        assertThat(pops).hasSize(4);
        assertThat(pop).isEqualTo("6");
    }
}
