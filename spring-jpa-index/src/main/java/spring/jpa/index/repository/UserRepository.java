package spring.jpa.index.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.index.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(final String name);
}
