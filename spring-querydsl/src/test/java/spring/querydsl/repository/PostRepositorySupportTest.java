package spring.querydsl.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.querydsl.domain.Post;

@SpringBootTest
class PostRepositorySupportTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositorySupport postRepositorySupport;

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

        final List<Post> posts = postRepositorySupport.findByTitle("test");

        assertAll(
                () -> assertThat(posts).hasSize(3)
        );
    }
}