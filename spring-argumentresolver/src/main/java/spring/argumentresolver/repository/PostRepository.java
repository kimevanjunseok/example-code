package spring.argumentresolver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.argumentresolver.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
