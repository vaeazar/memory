package com.maze.memory.service;

import com.maze.memory.domain.ClearInfo;
import com.maze.memory.repository.ClearRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 랭킹 서비스 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Service
@RequiredArgsConstructor
public class RankService {
  private final ClearRepository clearRepository;

  /**
   * <pre>
   *   최초 클리어 TOP 10의 클리어 정보를 조회한다.
   * </pre>
   *
   * @return 클리어 정보 리스트
   */
  public List<ClearInfo> getClearTop10() {
    return clearRepository.findTop10();
  }
}
