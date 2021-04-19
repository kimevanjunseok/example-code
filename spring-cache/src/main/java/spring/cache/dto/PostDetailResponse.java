package spring.cache.dto;

import spring.cache.domain.Post;

public class PostDetailResponse {

    private Long id;
    private String title;
    private String content;

    private PostDetailResponse(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static PostDetailResponse of(final Post post) {
        return new PostDetailResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
