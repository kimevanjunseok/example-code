package spring.redis.repository.acceptance;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import spring.redis.repository.config.TestRedisConfiguration;
import spring.redis.repository.dto.UserRequest;
import spring.redis.repository.dto.UserResponse;

@SpringBootTest(
        classes = TestRedisConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void manageUser() {
        // User 생성
        final UserRequest userRequest = new UserRequest("호랑이", 13);
        final UserResponse createdUser = post("/api/v1/users", userRequest, UserResponse.class);

        // User 찾기
        final UserResponse user = get("/api/v1/users/" + createdUser.getId(), UserResponse.class);

        // User 삭제
        delete("/api/v1/users/" + user.getId());
    }

    public <T> T post(final String path, final Object request, final Class<T> responseType) {
        return given()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post(path)
        .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(responseType);
    }

    public <T> T get(final String path, final Class<T> responseType) {
        return given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .get(path)
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(responseType);
    }

    public void delete(final String path) {
        given()
        .when()
                .delete(path)
        .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
