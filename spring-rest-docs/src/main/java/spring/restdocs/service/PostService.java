package spring.restdocs.service;

import org.springframework.stereotype.Service;

import spring.restdocs.dto.PostRequest;
import spring.restdocs.dto.PostResponse;
import spring.restdocs.repository.PostRepository;

@Service
public class PostService {

    private PostRepository postRepository;



    public PostResponse create(PostRequest postRequest) {
        return null;
    }
}
