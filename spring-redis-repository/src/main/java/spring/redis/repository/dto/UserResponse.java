package spring.redis.repository.dto;

import spring.redis.repository.domain.User;

public class UserResponse {

    private final String id;
    private final String name;
    private final int age;

    private UserResponse(final String id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static UserResponse of(final User user) {
        return new UserResponse(user.getId(), user.getName(), user.getAge());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
