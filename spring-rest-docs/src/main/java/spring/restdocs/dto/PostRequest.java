package spring.restdocs.dto;

public class PostRequest {

    private String title;
    private String content;

    protected PostRequest() {}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
