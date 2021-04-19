package spring.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.cache.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
