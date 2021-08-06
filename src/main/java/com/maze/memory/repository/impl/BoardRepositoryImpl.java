package com.maze.memory.repository.impl;

import com.maze.memory.domain.Board;
import com.maze.memory.repository.BoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import java.util.List;



public class BoardRepositoryImpl implements BoardRepository {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    //TODO QClass 생성안되서 보류
    @Override
    public Page<Board> findBuUserNo(long userNo) {
//        List<Board> resultList = queryFactory.select().from(QBoard.board)
        return null;
    }
}
