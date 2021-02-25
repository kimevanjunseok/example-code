package spring.dynamictest.dto;

import spring.dynamictest.domain.Post;

public class PostRequest {

    private String title;
    private String content;

    protected PostRequest() {}

    public PostRequest(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Post toEntity() {
        return new Post(title, content);
    }
}
