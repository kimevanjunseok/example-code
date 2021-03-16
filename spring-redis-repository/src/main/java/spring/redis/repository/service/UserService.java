package spring.redis.repository.service;

import org.springframework.stereotype.Service;

import spring.redis.repository.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
