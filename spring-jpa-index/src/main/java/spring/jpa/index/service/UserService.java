package spring.jpa.index.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.jpa.index.domain.User;
import spring.jpa.index.dto.UserCreateRequest;
import spring.jpa.index.dto.UserResponse;
import spring.jpa.index.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(final UserCreateRequest userCreateRequest) {
        final User user = userRepository.save(userCreateRequest.toEntity());
        return UserResponse.of(user);
    }

    public List<UserResponse> findAll() {
        final List<User> users = userRepository.findAll();
        return UserResponse.asList(users);
    }
}
