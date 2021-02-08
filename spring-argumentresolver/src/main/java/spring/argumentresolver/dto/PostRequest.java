package spring.argumentresolver.dto;

import spring.argumentresolver.domain.Post;

public class PostRequest {

    private String title;
    private String content;

    protected PostRequest() {}

    public Post toEntity() {
        return new Post(title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
