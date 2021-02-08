package spring.argumentresolver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.argumentresolver.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
