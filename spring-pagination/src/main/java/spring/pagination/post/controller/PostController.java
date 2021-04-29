package spring.pagination.post.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.pagination.post.dto.PostRequest;
import spring.pagination.post.dto.PostResponse;
import spring.pagination.post.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest postrequest) {
        final PostResponse postResponse = postService.create(postrequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).body(postResponse);
    }
}
