package spring.querydsl.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.querydsl.domain.Post;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositorySupport postRepositorySupport;

    @Test
    void findByTitle() {
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("test", "content"));
        postRepository.save(new Post("title1", "content"));
        postRepository.save(new Post("title2", "content"));
        postRepository.save(new Post("title3", "content"));

        final List<Post> posts = postRepositorySupport.findByTitle("test");

        assertAll(
                () -> assertThat(posts).hasSize(3)
        );
    }
}