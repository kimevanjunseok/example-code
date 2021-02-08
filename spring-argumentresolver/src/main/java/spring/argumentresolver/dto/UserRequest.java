package spring.argumentresolver.dto;

import spring.argumentresolver.domain.User;

public class UserRequest {

    private String identification;
    private String password;

    protected UserRequest() {}

    public UserRequest(final String identification, final String password) {
        this.identification = identification;
        this.password = password;
    }

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
