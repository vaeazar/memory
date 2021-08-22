package com.maze.memory.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * create on 2021/08/22. create by IntelliJ IDEA.
 *
 * <p> 방별로 정답이 저장된 프로퍼티를 가져오는 클래스 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@PropertySource("classpath:answer.properties")
@RequiredArgsConstructor
@Configuration
public class AnswerUtils {
  private final Environment environment;

  public String getAnswer(String roomId) {
    return environment.getProperty(roomId);
  }
}
