package com.maze.memory.utils;

import com.maze.memory.domain.response.DataResponseFormat;
import com.maze.memory.domain.response.ResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 응답 유틸리티 </p>
 * <p> 컨트롤러 응답을 구조화 한 클래스이다. </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public class ResponseUtils {
  public static ResponseEntity<ResponseFormat> getBadRequest(String message) {
    ResponseFormat res = ResponseFormat.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(message)
        .build();

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }

  public static <T> ResponseEntity<ResponseFormat> getResponseOk(T data) {
    ResponseFormat res = new DataResponseFormat<T>(HttpStatus.OK.value(),"response ok", data);
    return ResponseEntity.ok(res);
  }
}
