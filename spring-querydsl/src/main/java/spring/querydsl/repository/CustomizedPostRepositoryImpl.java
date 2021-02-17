package spring.querydsl.repository;

import static spring.querydsl.domain.QPost.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import spring.querydsl.domain.Post;

public class CustomizedPostRepositoryImpl implements CustomizedPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private CustomizedPostRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Post> findByTitle(final String title) {
        return jpaQueryFactory.selectFrom(post)
                .where(post.title.eq(title))
                .fetch();
    }
}