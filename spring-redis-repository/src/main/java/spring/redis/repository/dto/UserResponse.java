package spring.redis.repository.dto;

import spring.redis.repository.domain.User;

public class UserResponse {

    private final Long id;
    private final String email;
    private final String name;

    private UserResponse(final Long id, final String email, final String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
