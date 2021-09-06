package com.maze.memory.repository;

import com.maze.memory.domain.MemberInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<MemberInfo, Long> {

  @EntityGraph(attributePaths = "authorities")
  Optional<MemberInfo> findOneWithMemberRoleByMemberID(String memberID);
}
