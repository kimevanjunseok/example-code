package spring.restdocs.service;

import org.springframework.stereotype.Service;

import spring.restdocs.dto.PostRequest;
import spring.restdocs.dto.PostResponse;
import spring.restdocs.post.Post;
import spring.restdocs.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }
}
