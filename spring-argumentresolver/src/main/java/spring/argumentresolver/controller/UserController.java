package spring.argumentresolver.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.argumentresolver.dto.UserRequest;
import spring.argumentresolver.dto.UserResponse;
import spring.argumentresolver.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody final UserRequest userRequest) {
        final UserResponse userResponse = userService.create(userRequest);
        return ResponseEntity.created(URI.create("/users/" + userResponse.getId())).body(userResponse);
    }
}
