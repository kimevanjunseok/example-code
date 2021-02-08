package spring.argumentresolver.controller;

import org.springframework.web.bind.annotation.RestController;

import spring.argumentresolver.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
