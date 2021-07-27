package com.maze.memory.repository;

import com.maze.memory.domain.MemberInfo;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

  MemberInfo save(MemberInfo member);

  Optional<MemberInfo> findById(Long id);

  Optional<MemberInfo> findByMemberID(String memberId);

  Optional<MemberInfo> findByMemberIDAndPW(String memberID, String memberPW);

  List<MemberInfo> findAll();
}
