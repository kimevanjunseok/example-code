package spring.querydsl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.querydsl.domain.Post;
import spring.querydsl.dto.PostRequest;
import spring.querydsl.dto.PostResponse;
import spring.querydsl.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }

    public List<PostResponse> findAll() {
        final List<Post> posts = postRepository.findAll();
        return PostResponse.asList(posts);
    }
}
