package com.maze.memory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "BOARD_INFO", schema = "memory")
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "board_kind")
  private String boardKind;
  @Column(name = "board_header")
  private String boardHeader;
  @Column(name = "board_title")
  private String boardTitle;
  @Lob
  @Column(name = "board_content")
  private String boardContent;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "board_created_date")
  //ZonedDateTime = LocalDateTime + 시차/타임존, 날짜/시간 이외에 시차, 타임존이 필요할 경우 사용
  private ZonedDateTime boardCreateDate;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "board_last_modified_date")
  private ZonedDateTime boardLastModifiedDate;

  @Builder
  public Board (String boardKind, String boardHeader, String boardTitle,
                     String boardContent){
    this.boardKind = boardKind;
    this.boardHeader = boardHeader;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.boardCreateDate = ZonedDateTime.now();

  }

}
