package spring.querydsl.repository;

import java.util.List;

import spring.querydsl.domain.Post;

public interface CustomizedPostRepository {

    List<Post> findByTitle(final String title);

    List<Post> findDynamicQuery(final String title, final String content);
}
