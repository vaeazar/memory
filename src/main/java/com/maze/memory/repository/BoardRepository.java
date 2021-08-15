package com.maze.memory.repository;

import com.maze.memory.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BoardRepository가 JpaRepository, BoardRepositoryCustom extends
 * BoardRepositoryImple가 BoardRepositoryCustom implements
 * => BoardRepository 하나만 사용하기 위해서 위의 구조로 사용
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
