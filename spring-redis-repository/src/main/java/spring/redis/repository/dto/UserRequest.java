package spring.redis.repository.dto;

import spring.redis.repository.domain.User;

public class UserRequest {

    private String name;
    private int age;

    public User toEntity() {
        return new User(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
