package spring.querydsl.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.querydsl.dto.PostRequest;
import spring.querydsl.dto.PostResponse;
import spring.querydsl.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody final PostRequest postRequest) {
        final PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/")).body(postResponse);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }
}
