package spring.validation.service;

import org.springframework.stereotype.Service;

import spring.validation.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
