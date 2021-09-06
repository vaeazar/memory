package com.maze.memory.repository;

import com.maze.memory.domain.MemberInfo;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

  MemberInfo save(MemberInfo member);

  Optional<MemberInfo> findById(Long id);

  Optional<MemberInfo> findByMemberID(String memberId);

  Optional<MemberInfo> findByMemberIDAndPW(String memberID, String memberPW);

  String findMemberSaltByMemberID(String memberID);

  MemberInfo findMemberInfoByMemberID(String memberID);

  List<MemberInfo> findAll();
}
