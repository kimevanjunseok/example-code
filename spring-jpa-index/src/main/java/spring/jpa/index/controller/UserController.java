package spring.jpa.index.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.jpa.index.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
