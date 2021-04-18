package spring.cache.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.cache.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }
}
