package spring.redis.repository.dto;

import spring.redis.repository.domain.User;

public class UserRequest {

    private String email;
    private String password;
    private String name;

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
