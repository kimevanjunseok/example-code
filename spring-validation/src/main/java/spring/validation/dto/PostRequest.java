package spring.validation.dto;

import javax.validation.constraints.NotBlank;

import spring.validation.domain.Post;

public class PostRequest {

    @NotBlank
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
