package spring.restdocs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import spring.restdocs.dto.PostRequest;
import spring.restdocs.dto.PostResponse;
import spring.restdocs.post.Post;
import spring.restdocs.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }

    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    public PostResponse findById(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        return PostResponse.of(post);
    }

    public void update(final Long postId, final PostRequest postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        post.update(postRequest.toEntity());
    }

    public void delete(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        postRepository.delete(post);
    }
}
