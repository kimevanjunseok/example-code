package spring.cache.dto;

import java.util.List;
import java.util.stream.Collectors;

import spring.cache.domain.Post;

public class PostResponse {

    private Long id;
    private String title;

    private PostResponse(final Long id, final String title) {
        this.id = id;
        this.title = title;
    }

    public static PostResponse of(final Post post) {
        return new PostResponse(post.getId(), post.getTitle());
    }

    public static List<PostResponse> asList(final List<Post> posts) {
        return posts.stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
