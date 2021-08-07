package com.maze.memory.repository;

import com.maze.memory.domain.ClearInfo;
import java.util.List;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 미궁 클리어 정보 CRUD 레파지토리 </p>
 * <p> {@link ClearInfo}를 VO로 사용한다. </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public interface ClearRepository {

  /**
   * <pre>
   *   최초 클리어 TOP 10의 클리어 정보를 조회한다.
   * </pre>
   *
   * @return 클리어 정보 리스트
   */
  List<ClearInfo> findTop10();
}
