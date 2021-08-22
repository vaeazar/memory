package com.maze.memory.controller;

import com.maze.memory.domain.response.ResponseFormat;
import com.maze.memory.service.AnswerService;
import com.maze.memory.utils.AnswerUtils;
import com.maze.memory.utils.ResponseUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
@Controller
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerService service;
  private final AnswerUtils answerUtils;

  @PostMapping("/answer/check/{roomId}")
  public ResponseEntity<ResponseFormat> check(@PathVariable("roomId") String roomId, @RequestBody Map<String, Object> body) {
    Map<String, Object> data = new HashMap<>();
    try {
      if (answerUtils.getAnswer(roomId).equals(body.get("answer"))) {
        // 정답일 경우에 대한 처리
        service.success(roomId);
        data.put("isCorrect", true);
      } else {
        // 오답일 경우에 대한 처리
        service.fail(roomId, (String) body.get("answer"));
        data.put("isCorrect", false);
      }
      return ResponseUtils.getResponseOk(data);
    } catch (Exception e) {
      return ResponseUtils.getBadRequest(e.getMessage());
    }
  }
}
