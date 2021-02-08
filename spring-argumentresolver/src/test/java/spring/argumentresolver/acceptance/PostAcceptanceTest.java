package spring.argumentresolver.acceptance;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import spring.argumentresolver.dto.PostRequest;
import spring.argumentresolver.dto.PostResponse;
import spring.argumentresolver.dto.UserRequest;
import spring.argumentresolver.dto.UserResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void create() {
        final UserResponse userResponse = post("/users", new UserRequest("tigger", "1234"), UserResponse.class);

        final PostResponse postResponse = post("/posts", "userId",
                userResponse.getIdentification(), new PostRequest("title", "content"), PostResponse.class);

        assertAll(
                () -> assertThat(postResponse.getId()).isNotNull(),
                () -> assertThat(postResponse.getTitle()).isEqualTo("title"),
                () -> assertThat(postResponse.getContent()).isEqualTo("content"),
                () -> assertThat(postResponse.getUser()).isNotNull(),
                () -> assertThat(postResponse.getUser().getId()).isNotNull(),
                () -> assertThat(postResponse.getUser().getIdentification()).isEqualTo("tigger")
        );
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

    public <T> T post(final String path, final String headerName, final Object headerValue,
            final Object request, final Class<T> responseType) {
        return given()
                .header(headerName, headerValue)
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
}
