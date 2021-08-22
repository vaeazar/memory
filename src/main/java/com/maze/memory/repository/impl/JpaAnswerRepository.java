package com.maze.memory.repository.impl;

import com.maze.memory.domain.AnswerInfo;
import com.maze.memory.repository.AnswerRepository;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 2021/08/22. create by IntelliJ IDEA.
 *
 * <p> 미궁 정답 CRUD 레파지토리 </p>
 * <p> {@link AnswerRepository}의 JPA 구현체이다. </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Repository
@RequiredArgsConstructor
public class JpaAnswerRepository implements AnswerRepository {
  private final EntityManager em;

  @Override
  public AnswerInfo save(AnswerInfo answer) {
    em.persist(answer);
    return answer;
  }

  @Override
  public List<AnswerInfo> findByRoomId(String roomId) {
    String sql = "select a from AnswerInfo a where a.roomId = :roomId group by a.answer";
    return em.createQuery(sql, AnswerInfo.class)
        .setParameter("roomId", roomId)
        .getResultList();
  }
}
