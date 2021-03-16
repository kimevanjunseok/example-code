package spring.redis.repository.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.redis.repository.config.TestRedisConfiguration;
import spring.redis.repository.dto.UserRequest;
import spring.redis.repository.dto.UserResponse;

@SpringBootTest(classes = TestRedisConfiguration.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void save() {
        // given
        final UserRequest userRequest = new UserRequest("호랑이" , 13);

        // when
        final UserResponse userResponse = userService.save(userRequest);

        // than
        assertAll(
                () -> assertThat(userResponse).isNotNull(),
                () -> assertThat(userResponse.getId()).isNotNull(),
                () -> assertThat(userResponse.getName()).isEqualTo("호랑이"),
                () -> assertThat(userResponse.getAge()).isEqualTo(13)
        );
    }

    @Test
    void findById() {
        // given
        final UserRequest userRequest = new UserRequest("호랑이" , 13);
        final UserResponse userResponse = userService.save(userRequest);

        // when
        final UserResponse actual = userService.findById(userResponse.getId());

        // then
        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(userResponse.getId()),
                () -> assertThat(actual.getName()).isEqualTo(userResponse.getName()),
                () -> assertThat(actual.getAge()).isEqualTo(userResponse.getAge())
        );
    }

    @Test
    void delete() {
        // given
        final UserRequest userRequest = new UserRequest("호랑이" , 13);
        final UserResponse userResponse = userService.save(userRequest);

        // when
        userService.deleteById(userResponse.getId());

        // then
        assertThatThrownBy(
                () -> userService.findById(userResponse.getId())
        ).isInstanceOf(RuntimeException.class);
    }
}