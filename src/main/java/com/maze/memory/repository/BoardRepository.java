package com.maze.memory.repository;

import com.maze.memory.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//Board spring jpa로 구현
public interface BoardRepository extends JpaRepository<Board, Long> {

}
