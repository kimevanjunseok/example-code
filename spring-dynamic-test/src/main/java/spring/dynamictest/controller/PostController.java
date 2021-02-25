package spring.dynamictest.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.dynamictest.dto.PostRequest;
import spring.dynamictest.dto.PostResponse;
import spring.dynamictest.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest postRequest) {
        final PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).body(postResponse);
    }
}
