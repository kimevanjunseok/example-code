package spring.argumentresolver.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.argumentresolver.dto.PostRequest;
import spring.argumentresolver.dto.PostResponse;
import spring.argumentresolver.service.PostService;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    public ResponseEntity<PostResponse> create(@RequestBody final PostRequest postRequest) {
        final PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).body(postResponse);
    }
}
