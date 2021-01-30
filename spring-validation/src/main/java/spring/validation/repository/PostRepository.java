package spring.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.validation.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
