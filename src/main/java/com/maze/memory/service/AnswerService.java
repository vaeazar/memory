package com.maze.memory.service;

import com.maze.memory.domain.AnswerInfo;
import com.maze.memory.domain.ClearInfo;
import com.maze.memory.repository.AnswerRepository;
import com.maze.memory.repository.ClearRepository;
import java.util.Optional;
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

  public void success(String roomId, long spendTime) {
    // 멤버 정보 업데이트
    // TODO JWT 멤버정보 가져오기

    // 기존 클리어 정보 가져오기
    Optional<ClearInfo> oldClearInfoOpt = clearRepository.findByMemberAndRoom("younger33", roomId);

    if (oldClearInfoOpt.isEmpty()) { // 클리어 정보 없음
      // 새로운 클리어 정보 저장
      ClearInfo clearInfo = ClearInfo.builder()
          .memberId("younger33")
          .roomId(roomId)
          .spendTime(spendTime)
          .status("01")
          .build();
      clearRepository.save(clearInfo);
      return;
    }

    ClearInfo oldClearInfo = oldClearInfoOpt.get();
    if ("00".equals(oldClearInfo.getStatus())) { // 클리어 정보 있음
      // 이전 시도에서 클리어 하지 못한 경우
      ClearInfo clearInfo = ClearInfo.builder()
          .memberId("younger33")
          .roomId(roomId)
          .spendTime(oldClearInfo.getSpendTime() + spendTime)
          .status("01")
          .build();
      clearRepository.save(clearInfo);
    }
  }
}
