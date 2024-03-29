package spring.redis.repository.service;

import org.springframework.stereotype.Service;

import spring.redis.repository.domain.User;
import spring.redis.repository.dto.UserRequest;
import spring.redis.repository.dto.UserResponse;
import spring.redis.repository.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse save(final UserRequest userRequest) {
        final User user = userRepository.save(userRequest.toEntity());
        return UserResponse.of(user);
    }

    public UserResponse findById(final String userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);
        return UserResponse.of(user);
    }

    public void deleteById(final String userId) {
        userRepository.deleteById(userId);
    }
}
