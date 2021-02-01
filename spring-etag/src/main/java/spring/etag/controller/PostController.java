package spring.etag.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.etag.dto.PostRequest;
import spring.etag.dto.PostResponse;
import spring.etag.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody final PostRequest postRequest) {
        final PostResponse postResponse =  postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).body(postResponse);
    }

    @GetMapping("/etag")
    public ResponseEntity<List<PostResponse>> findAllWhenETag() {
        return ResponseEntity.ok().body(postService.findAll());
    }

    @GetMapping("/no-etag")
    public ResponseEntity<List<PostResponse>> findAllWhenNoETag() {
        return ResponseEntity.ok().body(postService.findAll());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        postService.update(postId, postRequest);
        return ResponseEntity.ok().build();
    }
}
