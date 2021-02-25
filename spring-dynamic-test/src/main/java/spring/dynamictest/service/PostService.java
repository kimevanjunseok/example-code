package spring.dynamictest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.dynamictest.domain.Post;
import spring.dynamictest.dto.PostRequest;
import spring.dynamictest.dto.PostResponse;
import spring.dynamictest.repository.PostRepository;
import spring.exception.PostNotFoundException;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(final PostRequest postRequest) {
        final Post post = postRepository.save(postRequest.toEntity());
        return PostResponse.of(post);
    }

    public List<PostResponse> findAll() {
        final List<Post> posts = postRepository.findAll();
        return PostResponse.asList(posts);
    }

    public PostResponse findById(final Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        return PostResponse.of(post);
    }

    public void delete(final Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));;
        postRepository.delete(post);
    }
}
