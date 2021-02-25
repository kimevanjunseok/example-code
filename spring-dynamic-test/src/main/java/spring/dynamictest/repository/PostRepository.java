package spring.dynamictest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.dynamictest.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
