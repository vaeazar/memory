package com.maze.memory.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

/**
 * create on 2021/08/07. create by IntelliJ IDEA.
 *
 * <p> 클리어 정보 Entity 클래스 </p>
 *
 * @author Yeonha Kim
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Entity
@Table(name = "clear_info", schema = "memory")
@Getter
public class ClearInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "member_id")
  private String memberId;

  @Column(name = "room_id")
  private String roomId;

  @Column(name = "spend_time")
  private Long spendTime;

  @CreationTimestamp
  @Column(name = "created_date")
  private LocalDateTime createdDate;
}
