package spring.swagger.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import spring.swagger.domain.Post;
import spring.swagger.dto.PostRequest;
import spring.swagger.dto.PostResponse;
import spring.swagger.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }

    public List<PostResponse> findAll() {
        final List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    public PostResponse findById(final Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        return PostResponse.of(post);
    }

    public void update(final Long postId, final PostRequest postRequest) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        post.update(postRequest.toEntity());
    }

    public void delete(final Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        postRepository.delete(post);
    }
}
