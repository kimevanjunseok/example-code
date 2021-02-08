package spring.argumentresolver.service;

import org.springframework.stereotype.Service;

import spring.argumentresolver.domain.Post;
import spring.argumentresolver.dto.PostRequest;
import spring.argumentresolver.dto.PostResponse;
import spring.argumentresolver.repository.PostRepository;

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
