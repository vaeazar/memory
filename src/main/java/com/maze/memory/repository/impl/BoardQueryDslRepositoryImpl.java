package com.maze.memory.repository.impl;

import com.maze.memory.domain.Board;
import com.maze.memory.repository.BoardQueryDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.maze.memory.domain.QBoard.board;


/**
* Query dsl 사용한
* @author hyejinzz
* @since 2021-08-09
**/
public class BoardQueryDslRepositoryImpl implements BoardQueryDslRepository {
    /**
     * setting에서 build 옵션이 gradle이 아닌 intelliJ라면 Q domain이 안생길 수 있음
     * 그럴경우 build에서 bookRunMainClassName 실행
     */
    private final JPAQueryFactory queryFactory;


    public BoardQueryDslRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Page<Board> findByUserNo(long userNo, Pageable pageable) {
        List<Board> result = queryFactory.selectFrom(board)
                .where(board.id.eq(userNo))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long cnt = queryFactory
                .selectFrom(board)
                .where(board.id.eq(userNo))
                .fetchCount();

        return new PageImpl<>(result, pageable, cnt);

    }


}
