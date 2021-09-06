package com.maze.memory.repository.impl;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {

  private final EntityManager em;

  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public MemberInfo save(MemberInfo memberInfo) {

    em.persist(memberInfo);
    return memberInfo;
  }

  @Override
  public Optional<MemberInfo> findById(Long id) {

    MemberInfo memberInfo = em.find(MemberInfo.class, id);
    return Optional.ofNullable(memberInfo);
  }

  @Override
  public Optional<MemberInfo> findByMemberID(String memberID) {
    List<MemberInfo> result = em.createQuery("select m from MemberInfo m where m.memberID = :memberID", MemberInfo.class)
        .setParameter("memberID", memberID)
        .getResultList();

    return result.stream().findFirst();

  }

  @Override
  public Optional<MemberInfo> findByMemberIDAndPW(String memberID, String memberPW) {
    List<MemberInfo> result = em.createQuery("select m from MemberInfo m where m.memberID = :memberID and m.memberPW = :memberPW", MemberInfo.class)
        .setParameter("memberID", memberID)
        .setParameter("memberPW", memberPW)
        .getResultList();

    return result.stream().findFirst();
  }

  @Override
  public String findMemberSaltByMemberID(String memberID) {

    return em.createQuery("select m from MemberInfo m where m.memberID = :memberID", MemberInfo.class)
        .setParameter("memberID", memberID)
        .getSingleResult().getMemberSalt();
  }

  @Override
  public MemberInfo findMemberInfoByMemberID(String memberID) {

    return em.createQuery("select m from MemberInfo m where m.memberID = :memberID", MemberInfo.class)
        .setParameter("memberID", memberID)
        .getSingleResult();
  }

  @Override
  public List<MemberInfo> findAll() {

    return em.createQuery("select m from MemberInfo m", MemberInfo.class)
        .getResultList();
  }
}
