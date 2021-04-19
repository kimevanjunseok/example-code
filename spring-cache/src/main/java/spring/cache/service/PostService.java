package spring.cache.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import spring.cache.domain.Post;
import spring.cache.dto.PostDetailResponse;
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

    @Cacheable(value = "Post")
    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return PostResponse.asList(posts);
    }

    @Cacheable(value = "PostDetail", key = "#postId")
    public PostDetailResponse findById(final Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        return PostDetailResponse.of(post);
    }

    @CachePut(value = "PostDetail", key = "#postId")
    @CacheEvict(value = "Post", allEntries = true)
    public void update(final Long postId, final PostRequest postRequest) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        post.update(postRequest.toEntity());
    }

    @Caching(evict = {
            @CacheEvict(value = "Post", allEntries = true),
            @CacheEvict(value = "PostDetail", key = "#postId")
    })
    public void delete(final Long postId) {
        postRepository.deleteById(postId);
    }
}
