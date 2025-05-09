package com.toyproject.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toyproject.board.domain.QPost;
import com.toyproject.board.dto.PostListDto;
import com.toyproject.board.dto.PostSearchCond;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toyproject.board.domain.QPost.*;

public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<PostListDto> search(PostSearchCond condition, Pageable pageable) {
        List<PostListDto> content = queryFactory
                .select(Projections.fields(PostListDto.class,
                        post.id,
                        post.title,
                        post.writer,
                        post.content,
                        post.createdDate
                ))
                .from(post)
                .where(titleLike(condition.getTitle()), writerEq(condition.getWriter()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(titleLike(condition.getTitle()), writerEq(condition.getWriter()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleLike(String titleCond) {
        return StringUtils.hasText(titleCond) ? post.title.like("%" + titleCond + "%") : null;
    }

    private BooleanExpression writerEq(String writerCond) {
        return StringUtils.hasText(writerCond) ? post.writer.eq(writerCond) : null;
    }

}
