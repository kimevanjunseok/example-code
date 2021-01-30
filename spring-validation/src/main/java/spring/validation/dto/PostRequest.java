package spring.validation.dto;

import javax.validation.constraints.NotBlank;

import spring.validation.domain.Post;

public class PostRequest {

    @NotBlank(message = "Post's title must not be blank")
    private String title;
    private String content;

    protected PostRequest() {}

    public PostRequest(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

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
