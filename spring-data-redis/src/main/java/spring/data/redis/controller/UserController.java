package spring.data.redis.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.data.redis.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
