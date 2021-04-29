package spring.pagination.post.service;

import org.springframework.stereotype.Service;

import spring.pagination.post.domain.Post;
import spring.pagination.post.dto.PostRequest;
import spring.pagination.post.dto.PostResponse;
import spring.pagination.post.repository.PostRepository;

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
}
