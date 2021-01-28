package spring.restdocs.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.restdocs.dto.PostRequest;
import spring.restdocs.dto.PostResponse;
import spring.restdocs.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity create(@RequestBody final PostRequest postRequest) {
        PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/")).body(postResponse);
    }
}
