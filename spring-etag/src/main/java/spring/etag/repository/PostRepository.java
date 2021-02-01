package spring.etag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.etag.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
