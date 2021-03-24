package spring.data.redis.service;

import org.springframework.stereotype.Service;

import spring.data.redis.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
