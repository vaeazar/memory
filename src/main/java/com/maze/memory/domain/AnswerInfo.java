package com.maze.memory.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * create on 2021/08/22. create by IntelliJ IDEA.
 *
 * <p> 문제별 제출 정답 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Entity
@Table(name = "answer_info", schema = "memory")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "member_id")
  private String memberId;

  @Column(name = "room_id")
  private String roomId;

  @Column(name = "answer")
  private String answer;

  @CreationTimestamp
  @Column(name = "created_date")
  private LocalDateTime createdDate;
}
