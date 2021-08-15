package com.maze.memory.repository;

import com.maze.memory.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepositoryCustom {

    //모든 게시글 검색
//    Page<Board> findAll(Pageable pageable);

    //유저 이름으로 게시글 검색
    Page<Board> findByUserNo(long userNo, Pageable pageable);

    //머릿말로 게시글 검색
    Page<Board> findByBoardHeader(String boardHeader, Pageable pageable);

    //글 제목/내용로 게시글 검색
    Page<Board> findByContents(String contents, String searcyType, Pageable pageable);


}
