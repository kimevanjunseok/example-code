package spring.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.querydsl.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>, CustomizedPostRepository {
}
