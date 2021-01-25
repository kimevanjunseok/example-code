package spring.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.swagger.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
