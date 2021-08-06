package com.maze.memory.repository;

import com.maze.memory.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardRepository {

    //유저 이름으로 검색
    Page<Board> findBuUserNo(long userNo);

}
