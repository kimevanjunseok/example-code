package spring.argumentresolver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.argumentresolver.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentification(final String identification);
}
