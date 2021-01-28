package spring.restdocs.dto;

public class PostResponse {

    private Long id;
    private String title;
    private String content;

    private PostResponse(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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
