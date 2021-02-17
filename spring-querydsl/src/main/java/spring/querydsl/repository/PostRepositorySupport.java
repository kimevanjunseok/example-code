package spring.querydsl.repository;

import static spring.querydsl.domain.QPost.*;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import spring.querydsl.domain.Post;

@Repository
public class PostRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Post> findByTitle(final String title) {
        return jpaQueryFactory.selectFrom(post)
                .where(post.title.eq(title))
                .fetch();
    }
}
