package spring.pagination.post.service;

import org.springframework.stereotype.Service;

import spring.pagination.post.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
