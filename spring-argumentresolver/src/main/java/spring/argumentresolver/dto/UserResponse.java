package spring.argumentresolver.dto;

import spring.argumentresolver.domain.User;

public class UserResponse {

    private Long id;
    private String identification;

    private UserResponse(final Long id, final String identification) {
        this.id = id;
        this.identification = identification;
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(), user.getIdentification());
    }

    public Long getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }
}
