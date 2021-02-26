package spring.jpa.index.dto;

import java.util.List;
import java.util.stream.Collectors;

import spring.jpa.index.domain.User;

public class UserResponse {

    private Long id;
    private String email;
    private String name;

    private UserResponse(final Long id, final String email, final String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

    public static List<UserResponse> asList(final List<User> users) {
        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
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
