package spring.argumentresolver.service;

import org.springframework.stereotype.Service;

import spring.argumentresolver.domain.User;
import spring.argumentresolver.dto.UserRequest;
import spring.argumentresolver.dto.UserResponse;
import spring.argumentresolver.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(final UserRequest userRequest) {
        final User user = userRepository.save(userRequest.toEntity());
        return UserResponse.of(user);
    }

    public User findByIdentification(final String identification) {
        return userRepository.findByIdentification(identification)
                .orElseThrow(IllegalArgumentException::new);
    }
}
