package spring.querydsl.repository;

import static spring.querydsl.domain.QPost.*;

import java.util.List;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
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

    @Override
    public List<Post> findDynamicQuery(final String title, final String content) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!ObjectUtils.isEmpty(title)) {
            booleanBuilder.and(post.title.eq(title));
        }

        if (!ObjectUtils.isEmpty(content)) {
            booleanBuilder.and(post.content.eq(content));
        }

        return jpaQueryFactory.selectFrom(post)
                .where(booleanBuilder)
                .fetch();
    }
}