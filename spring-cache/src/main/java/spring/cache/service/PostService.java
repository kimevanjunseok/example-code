package spring.cache.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.cache.domain.Post;
import spring.cache.dto.PostRequest;
import spring.cache.dto.PostResponse;
import spring.cache.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }

    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return PostResponse.asList(posts);
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
        postRepository.deleteById(postId);
    }
}
