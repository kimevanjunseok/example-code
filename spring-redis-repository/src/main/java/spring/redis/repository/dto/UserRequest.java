package spring.redis.repository.dto;

import spring.redis.repository.domain.User;

public class UserRequest {

    private String name;
    private int age;

    public UserRequest(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public User toEntity() {
        return new User(null, name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
