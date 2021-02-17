package spring.querydsl.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("title1", "content"));
        postRepository.save(new Post("title2", "content"));
        postRepository.save(new Post("title3", "content"));

        final List<Post> posts = postRepository.findByTitle("test");

        assertAll(
                () -> assertThat(posts).hasSize(3)
        );
    }
}