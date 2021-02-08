package spring.argumentresolver.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.argumentresolver.controller.annotation.Login;
import spring.argumentresolver.domain.User;
import spring.argumentresolver.dto.PostRequest;
import spring.argumentresolver.dto.PostResponse;
import spring.argumentresolver.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@Login final User user, @RequestBody final PostRequest postRequest) {
        final PostResponse postResponse = postService.create(user, postRequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).body(postResponse);
    }
}
