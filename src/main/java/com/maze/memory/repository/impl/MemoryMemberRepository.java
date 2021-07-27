package com.maze.memory.repository.impl;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.repository.MemberRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository {

  private static Map<AtomicLong, MemberInfo> store = new ConcurrentHashMap<>();

  private static long sequence = 0L;


  @Override
  public MemberInfo save(MemberInfo member) {
    return null;
  }

  @Override
  public Optional<MemberInfo> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<MemberInfo> findByMemberID(String memberId) {
    return Optional.empty();
  }

  @Override
  public Optional<MemberInfo> findByMemberIDAndPW(String memberID, String memberPW) {
    return Optional.empty();
  }

  @Override
  public List<MemberInfo> findAll() {
    return null;
  }
}
