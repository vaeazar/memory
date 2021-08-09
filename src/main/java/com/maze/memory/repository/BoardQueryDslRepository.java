package com.maze.memory.repository;

import com.maze.memory.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardQueryDslRepository {

    //유저 이름으로 검색
    Page<Board> findByUserNo(long userNo, Pageable pageable);
}
