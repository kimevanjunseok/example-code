package spring.argumentresolver.dto;

import spring.argumentresolver.domain.User;

public class UserResponse {

    private Long id;
    private String identification;
    private String password;

    private UserResponse(final Long id, final String identification, final String password) {
        this.id = id;
        this.identification = identification;
        this.password = password;
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(), user.getIdentification(), user.getPassword());
    }

    public Long getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }
}
