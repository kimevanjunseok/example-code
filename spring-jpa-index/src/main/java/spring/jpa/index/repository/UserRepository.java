package spring.jpa.index.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.index.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
