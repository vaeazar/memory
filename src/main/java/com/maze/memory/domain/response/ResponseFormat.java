package com.maze.memory.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> REST API 응답 포맷 클래스 </p>
 * <p> {@link ResponseEntity}과 함께 사용한다.</p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Getter
@RequiredArgsConstructor
@Builder
public class ResponseFormat {
  // 응답코드
  private final int code;
  // 응답 메세지
  private final String message;
}
