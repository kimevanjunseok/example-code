package spring.jpa.index.dto;

import spring.jpa.index.domain.User;

public class UserCreateRequest {

    private String email;
    private String password;
    private String name;

    protected UserCreateRequest() {}

    public User toEntity() {
        return new User(email, password, name);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
