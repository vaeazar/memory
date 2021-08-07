package com.maze.memory.controller;

import com.maze.memory.domain.ClearInfo;
import com.maze.memory.domain.ResponseFormat;
import com.maze.memory.service.RankService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 랭킹 컨트롤러 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@RestController
@RequestMapping(value = "/rank")
@RequiredArgsConstructor
public class RankController {
  private final RankService service;

  @GetMapping(value = "/clear/top10", produces = "application/json")
  public ResponseEntity<ResponseFormat<Object>> getClearTop10() {
    try {
      List<ClearInfo> infos = service.getAllClearTop10();

      Map<String, Object> data = new HashMap<>();
      data.put("infos", infos);

      ResponseFormat<Object> res = ResponseFormat.builder()
          .code(HttpStatus.OK.value())
          .message("request ok")
          .data(data)
          .build();

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      ResponseFormat<Object> res = ResponseFormat.builder()
          .code(HttpStatus.BAD_REQUEST.value())
          .message(e.getMessage())
          .build();

      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/clear/room/{roomId}", produces = "application/json")
  public ResponseEntity<ResponseFormat<Object>> getClearTime(
      @PathVariable("roomId") String roomId,
      @RequestParam(required = false) String memberId,
      @RequestParam(required = false, defaultValue = "10") int limit) {
    try {
      List<ClearInfo> infos = service.getClearSpendTimeTop5(roomId);

      Map<String, Object> data = new HashMap<>();
      data.put("infos", infos);

      ResponseFormat<Object> res = ResponseFormat.builder()
          .code(HttpStatus.OK.value())
          .message("request ok")
          .data(data)
          .build();

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      ResponseFormat<Object> res = ResponseFormat.builder()
          .code(HttpStatus.BAD_REQUEST.value())
          .message(e.getMessage())
          .build();

      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/answer/wrong", produces = "application/json")
  public Map<String, Object> getWrongAnswer(@RequestParam(required = false) String memberId) {
    Map<String, Object> res = new HashMap<>();

    res.put("memberId", memberId);

    return res;
  }
}
