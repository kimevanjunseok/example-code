package spring.dynamictest.service;

import org.springframework.stereotype.Service;

import spring.dynamictest.domain.Post;
import spring.dynamictest.dto.PostRequest;
import spring.dynamictest.dto.PostResponse;
import spring.dynamictest.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }
}
