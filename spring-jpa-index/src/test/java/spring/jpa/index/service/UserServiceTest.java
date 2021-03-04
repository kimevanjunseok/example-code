package spring.jpa.index.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import spring.jpa.index.dto.UserResponse;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private StopWatch stopWatch;

    @BeforeEach
    void setUp() {
        stopWatch = new StopWatch("DB Test");
    }

    @Test
    void findByName() {
        stopWatch.start("DB 조회");
        UserResponse userResponses = userService.findByName("Suzanne Barhams");
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        assertThat(userResponses.getName()).isEqualTo("Suzanne Barhams");
    }
}