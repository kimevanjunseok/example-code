package spring.querydsl.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import spring.querydsl.confg.TestQueryDSLConfig;
import spring.querydsl.domain.Post;

@DataJpaTest
@Import(TestQueryDSLConfig.class)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void tearDown() {
        postRepository.deleteAllInBatch();
    }

    @Test
    void findByTitle() {
        postRepository.saveAll(Arrays.asList(
                new Post("test", "content"),
                new Post("test", "content"),
                new Post("test", "content"),
                new Post("title1", "content"),
                new Post("title2", "content"),
                new Post("title3", "content")
        ));

        final List<Post> posts = postRepository.findByTitle("test");

        assertAll(
                () -> assertThat(posts).hasSize(3)
        );
    }

    @Test
    void findDynamicQuery() {
        postRepository.saveAll(Arrays.asList(
                new Post("TestTitle", "content"),
                new Post("TestTitle", "content"),
                new Post("title", "TestContent"),
                new Post("title", "TestContent"),
                new Post("TestTitle", "TestContent"),
                new Post("TestTitle", "TestContent")
        ));

        final List<Post> posts = postRepository.findDynamicQuery("TestTitle", "TestContent");

        assertAll(
                () -> assertThat(posts).hasSize(2),
                () -> assertThat(posts.get(0).getTitle()).isEqualTo("TestTitle"),
                () -> assertThat(posts.get(0).getContent()).isEqualTo("TestContent")
        );
    }

    @Test
    void findDynamicQueryAdvance() {
        postRepository.saveAll(Arrays.asList(
                new Post("TestTitle", "content"),
                new Post("TestTitle", "content"),
                new Post("title", "TestContent"),
                new Post("title", "TestContent"),
                new Post("TestTitle", "TestContent"),
                new Post("TestTitle", "TestContent")
        ));

        final List<Post> posts = postRepository.findDynamicQueryAdvance("TestTitle", "TestContent");

        assertAll(
                () -> assertThat(posts).hasSize(2),
                () -> assertThat(posts.get(0).getTitle()).isEqualTo("TestTitle"),
                () -> assertThat(posts.get(0).getContent()).isEqualTo("TestContent")
        );
    }
}