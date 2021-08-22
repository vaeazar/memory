package com.maze.memory.repository;

import com.maze.memory.domain.AnswerInfo;
import java.util.List;

/**
 * create on 2021/08/22. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public interface AnswerRepository {
  AnswerInfo save(AnswerInfo answer);
  List<AnswerInfo> findByRoomId(String roomId);
}
