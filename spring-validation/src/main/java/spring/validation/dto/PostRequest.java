package spring.validation.dto;

import javax.validation.constraints.NotBlank;

public class PostRequest {

    @NotBlank
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
