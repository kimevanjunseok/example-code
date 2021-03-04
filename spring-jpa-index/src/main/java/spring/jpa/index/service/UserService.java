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

    public UserResponse findByName(final String name) {
        final User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        return UserResponse.of(user);
    }
}
