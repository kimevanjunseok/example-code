package spring.cache.dto;

import spring.cache.domain.Post;

public class PostRequest {

    private String title;
    private String content;

    protected PostRequest() {}

    public Post toEntity() {
        return new Post(this.title, this.content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
