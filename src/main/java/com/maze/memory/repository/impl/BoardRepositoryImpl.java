package com.maze.memory.repository.impl;

import com.maze.memory.domain.Board;
import com.maze.memory.repository.BoardRepositoryCustom;
import com.maze.memory.utils.MazeConstant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.maze.memory.domain.QBoard.board;


@RequiredArgsConstructor

/**
 * setting에서 build 옵션이 gradle이 아닌 intelliJ라면 Q domain이 안생길 수 있음
 * 그럴경우 build에서 bookRunMainClassName 실행
 */
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //모든 게시글 검색
    @Override
    public Page<Board> findAll(Pageable pageable) {
        List<Board> result = queryFactory.selectFrom(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long cnt = queryFactory
                .selectFrom(board)
                .fetchCount();

        return new PageImpl<>(result, pageable, cnt);
    }

    //    유저 이름으로 게시글 검색
    @Override
    public Page<Board> findByUserNo(long userNo, Pageable pageable) {
        List<Board> result = queryFactory.selectFrom(board)
                .where(board.id.eq(userNo))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.boardCreateDate.desc())
                .fetch();

        long cnt = queryFactory
                .selectFrom(board)
                .where(board.id.eq(userNo))
                .fetchCount();

        return new PageImpl<>(result, pageable, cnt);

    }

    //머릿말로 검색
    @Override
    public Page<Board> findByBoardHeader(String header, Pageable pageable) {
        List<Board> result = queryFactory.selectFrom(board)
                .where(eqBoardHeade(header))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.boardCreateDate.desc())
                .fetch();

        long cnt = queryFactory
                .selectFrom(board)
                .where(eqBoardHeade(header))
                .fetchCount();

        return new PageImpl<>(result, pageable, cnt);

    }


    //글 제목, 게시글 + 글제목 으로 검색
    @Override
    public Page<Board> findByContents(String contents, String searcyType, Pageable pageable) {
        List<Board> result = queryFactory.selectFrom(board)
                .where(eqContents(contents, searcyType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.boardCreateDate.desc())
                .fetch();

        long cnt = queryFactory
                .selectFrom(board)
                .where(eqContents(contents, searcyType))
                .fetchCount();

        return new PageImpl<>(result, pageable, cnt);
    }

    private BooleanExpression eqBoardHeade(String header) {

        if (header.equals(MazeConstant.NOTI)) {
            return board.boardHeader.eq(MazeConstant.NOTI);
        }
        if (header.equals(MazeConstant.FREE)) {
            return board.boardHeader.eq(MazeConstant.FREE);
        }
        if (header.equals(MazeConstant.QUESTION)) {
            return board.boardHeader.eq(MazeConstant.QUESTION);
        } else return null;

    }

    private BooleanExpression eqContents(String contents, String searchType) {

        //제목만 검색
        if (searchType.equals(MazeConstant.FIND_TITLE)) {
            return board.boardTitle.contains(contents);

        }
        //제목과 내용으로 검색
        return board.boardTitle.contains(contents).or(board.boardContent.contains(contents));

    }
}
