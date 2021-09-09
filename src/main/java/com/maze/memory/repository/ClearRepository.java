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

  /**
   * <pre>
   *   방 클리어 소요시간 TOP5의 클리어 정보를 조회한다.
   * </pre>
   *
   * @param roomId 방 아이디
   * @return 클리어 정보 리스트
   */
  List<ClearInfo> findTop5ByRoomId(String roomId);

  /**
   * <pre>
   *   클리어 정보를 저장한다.
   * </pre>
   * @param clear 클리어 정보
   * @return 저장된 클리어 정보
   */
  ClearInfo save(ClearInfo clear);

  /**
   * <pre>
   *   유저 아이디와 방 아이디로 클리어 정보를 조회한다.
   * </pre>
   * @param memberId 유저 아이디
   * @param roomId 방 아이디
   * @return 클리어 정보
   */
  ClearInfo findByMemberAndRoom(String memberId, String roomId);
}
