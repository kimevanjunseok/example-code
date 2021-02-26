package spring.jpa.index.service;

import org.springframework.stereotype.Service;

import spring.jpa.index.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
