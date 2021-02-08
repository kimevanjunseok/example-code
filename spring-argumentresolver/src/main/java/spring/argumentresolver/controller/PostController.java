package spring.argumentresolver.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.argumentresolver.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }
}
