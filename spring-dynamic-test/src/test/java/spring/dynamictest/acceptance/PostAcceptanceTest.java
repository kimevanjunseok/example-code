package spring.dynamictest.acceptance;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import spring.dynamictest.dto.PostRequest;
import spring.dynamictest.dto.PostResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @TestFactory
    Stream<DynamicTest> dynamicTests() {
        return Stream.of(
                dynamicTest("게시글을 생성한다.", () -> {
                    create(new PostRequest("title1", "content1"), "/posts", PostResponse.class);
                    create(new PostRequest("title2", "content2"), "/posts", PostResponse.class);
                    create(new PostRequest("title3", "content3"), "/posts", PostResponse.class);

                    final List<PostResponse> postResponse = getAll("/posts", PostResponse.class);

                    assertThat(postResponse).hasSize(3);
                }),
                dynamicTest("게시글을 전체 조회한다.", () -> {
                    final List<PostResponse> postResponse = getAll("/posts", PostResponse.class);

                    assertAll(
                            () -> assertThat(postResponse.get(0)).isNotNull(),
                            () -> assertThat(postResponse.get(0).getId()).isNotNull(),
                            () -> assertThat(postResponse.get(1)).isNotNull(),
                            () -> assertThat(postResponse.get(1).getId()).isNotNull(),
                            () -> assertThat(postResponse.get(2)).isNotNull(),
                            () -> assertThat(postResponse.get(2).getId()).isNotNull()
                    );
                }),
                dynamicTest("게시글을 하나 조회한다.", () -> {
                    final PostResponse postResponse = getById("/posts/{postId}", 1L, PostResponse.class);

                    assertAll(
                            () -> assertThat(postResponse).isNotNull(),
                            () -> assertThat(postResponse.getId()).isEqualTo(1L),
                            () -> assertThat(postResponse.getTitle()).isEqualTo("title1"),
                            () -> assertThat(postResponse.getContent()).isEqualTo("content1")
                    );
                }),
                dynamicTest("게시글을 하나 삭제한다.", () -> {
                    delete("/posts/{postId}", 1L);

                    get_StatusCode400("/posts/{postId}", 1L);
                })
        );
    }

    private <T> T create(final Object request, final String path, final Class<T> responseType) {
        return given()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post(path)
        .then()
                .log().all()
                .statusCode(201)
                .extract().as(responseType);
    }

    private <T> List<T> getAll(final String path, final Class<T> responseType) {
        return given()
        .when()
                .get("/posts")
        .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getList(".", responseType);
    }

    private <T> T getById(final String path, final Long postId, final Class<T> responseType) {
        return given()
        .when()
                .get(path, postId)
        .then()
                .log().all()
                .statusCode(200)
                .extract().as(responseType);
    }

    private void delete(final String path, final Long postId) {
        given()
        .when()
                .delete(path, postId)
        .then()
                .log().all()
                .statusCode(204);
    }

    private void get_StatusCode400(final String path, final Long postId) {
        given()
        .when()
                .get(path, postId)
        .then()
                .log().all()
                .statusCode(400);
    }
}
