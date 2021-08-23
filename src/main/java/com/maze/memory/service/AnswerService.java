package com.maze.memory.service;

import com.maze.memory.domain.AnswerInfo;
import com.maze.memory.domain.ClearInfo;
import com.maze.memory.domain.MemberInfo;
import com.maze.memory.repository.AnswerRepository;
import com.maze.memory.repository.ClearRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
  private final AnswerRepository answerRepository;
  private final ClearRepository clearRepository;

  public void fail(String roomId, String answer) {
    // 오답 정보 저장
    AnswerInfo answerInfo = AnswerInfo.builder()
        .memberId("younger33")
        .roomId(roomId)
        .answer(answer)
        .build();
    answerRepository.save(answerInfo);
  }

  public void success(String roomId) {
    // 멤버 정보 업데이트
    // TODO JWT 멤버정보 가져오기

    // 클리어 정보 저장
    ClearInfo clearInfo = ClearInfo.builder()
        .memberId("younger33")
        .roomId(roomId)
        .spendTime(156783215L)
        .build();
    clearRepository.save(clearInfo);
  }
}
