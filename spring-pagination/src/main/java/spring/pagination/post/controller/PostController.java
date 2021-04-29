package spring.pagination.post.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.pagination.post.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }
}
