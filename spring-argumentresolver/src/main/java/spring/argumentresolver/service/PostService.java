package spring.argumentresolver.service;

import org.springframework.stereotype.Service;

import spring.argumentresolver.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
