package spring.validation.service;

import org.springframework.stereotype.Service;

import spring.validation.domain.Post;
import spring.validation.dto.PostRequest;
import spring.validation.dto.PostResponse;
import spring.validation.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }
}
