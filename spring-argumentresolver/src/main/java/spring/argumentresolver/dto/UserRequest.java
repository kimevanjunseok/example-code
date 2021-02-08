package spring.argumentresolver.dto;

import spring.argumentresolver.domain.User;

public class UserRequest {

    private String identification;
    private String password;

    protected UserRequest() {}

    public User toEntity() {
        return new User(identification, password);
    }

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }
}
