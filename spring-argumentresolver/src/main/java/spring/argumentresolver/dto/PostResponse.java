package spring.argumentresolver.dto;

import spring.argumentresolver.domain.Post;

public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private UserResponse user;

    private PostResponse(final Long id, final String title, final String content, final UserResponse user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public static PostResponse of(final Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), UserResponse.of(post.getUser()));
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

    public UserResponse getUser() {
        return user;
    }
}
