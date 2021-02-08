package spring.argumentresolver.service;

import org.springframework.stereotype.Service;

import spring.argumentresolver.domain.Post;
import spring.argumentresolver.domain.User;
import spring.argumentresolver.dto.PostRequest;
import spring.argumentresolver.dto.PostResponse;
import spring.argumentresolver.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final User user, final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity(user));
        return PostResponse.of(post);
    }
}
