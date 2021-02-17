package spring.querydsl.repository;

import java.util.List;

import spring.querydsl.domain.Post;

public interface PostRepositoryCustom {

    List<Post> findByTitle(final String title);
}
