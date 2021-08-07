package com.maze.memory.repository.impl;

import com.maze.memory.domain.ClearInfo;
import com.maze.memory.repository.ClearRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 미궁 클리어 정보 CRUD 레파지토리 </p>
 * <p> {@link ClearRepository}의 JPA 구현체이다. </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Repository
@RequiredArgsConstructor
public class JpaClearRepository implements ClearRepository {

  private final EntityManager em;

  @Override
  public List<ClearInfo> findTop10() {
    String query = "select c from ClearInfo c where c.roomId = 'r20' order by c.createdDate desc";
    List<ClearInfo> result = em.createQuery(query, ClearInfo.class).getResultList();

    return result.stream().limit(10).collect(Collectors.toList());
  }
}
