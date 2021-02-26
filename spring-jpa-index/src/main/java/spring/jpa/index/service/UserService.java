package spring.jpa.index.service;

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
}
