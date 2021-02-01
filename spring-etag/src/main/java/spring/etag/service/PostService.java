package spring.etag.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.etag.domain.Post;
import spring.etag.dto.PostRequest;
import spring.etag.dto.PostResponse;
import spring.etag.repository.PostRepository;

@Transactional
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
        final List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    public void update(final Long postId, final PostRequest postRequest) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        post.update(postRequest.toEntity());
    }
}
