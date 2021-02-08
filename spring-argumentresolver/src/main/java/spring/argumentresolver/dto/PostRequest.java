package spring.argumentresolver.dto;

import spring.argumentresolver.domain.Post;
import spring.argumentresolver.domain.User;

public class PostRequest {

    private String title;
    private String content;

    protected PostRequest() {}

    public PostRequest(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(final User user) {
        return new Post(title, content, user);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
