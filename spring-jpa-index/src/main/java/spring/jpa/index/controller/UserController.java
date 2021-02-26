package spring.jpa.index.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.index.dto.UserCreateRequest;
import spring.jpa.index.dto.UserResponse;
import spring.jpa.index.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest userCreateRequest) {
        final UserResponse userResponse = userService.create(userCreateRequest);
        return ResponseEntity.created(URI.create("/users/" + userResponse.getId())).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
