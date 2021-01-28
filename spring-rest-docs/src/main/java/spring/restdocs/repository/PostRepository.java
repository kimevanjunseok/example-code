package spring.restdocs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.restdocs.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
