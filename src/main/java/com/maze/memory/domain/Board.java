package com.maze.memory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.maze.memory.dto.BoardRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Data
@Table(name = "board", schema = "memory")
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //게시판 종류
  @Column(name = "board_kind")
  private String boardKind;

  //머릿말
//  @Enumerated(EnumType.STRING)
  @Column(name = "board_header")
  private String boardHeader;

  //게시글 종류
  @Column(name = "board_title")
  private String boardTitle;

  //게시글 내용
  @Lob
  @Column(name = "board_content")
  private String boardContent;


  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "board_created_date")
  //게시글 생성 날짜
  private LocalDateTime boardCreateDate;


  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  //게시글수정 날짜
  @Column(name = "board_last_modified_date")
  private LocalDateTime boardLastModifiedDate;

  //업데이트
  public void updateBoard(BoardRequest rq) {
    this.boardKind = rq.getBoardKind();
    this.boardHeader = rq.getBoardHeader();
    this.boardTitle = rq.getBoardTitle();
    this.boardContent = rq.getBoardContent();
    this.boardLastModifiedDate = LocalDateTime.now();
  }

  @Builder
  public Board(String boardKind, String boardHeader, String boardTitle, String boardContent, LocalDateTime boardCreateDate) {
    this.boardKind = boardKind;
    this.boardHeader = boardHeader;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.boardCreateDate = LocalDateTime.now();
  }

}
