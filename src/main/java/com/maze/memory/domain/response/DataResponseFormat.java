package com.maze.memory.domain.response;

import lombok.Getter;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 데이터를 포함하는 응답 포맷 클래스 </p>
 * <p> {@link ResponseFormat}을 상속 받았다. </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Getter
public class DataResponseFormat<T> extends ResponseFormat {
  // 응답 데이터
  private final T data;

  public DataResponseFormat(int code, String message, T data) {
    super(code, message);
    this.data = data;
  }
}