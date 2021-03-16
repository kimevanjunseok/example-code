package spring.redis.repository.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.redis.repository.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
