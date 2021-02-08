package spring.argumentresolver.service;

import org.springframework.stereotype.Service;

import spring.argumentresolver.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
