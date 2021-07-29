package com.maze.memory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import lombok.Data;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.jpa.repository.Temporal;

@Entity
@Data
@Table(name = "BOARD_INFO", schema = "memory")
public class BoardInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BOARD_KIND")
  String memberID;
  @Column(name = "BOARD_HEADER")
  String memberPW;
  @Column(name = "BOARD_TITLE")
  String memberSalt;
  @Lob
  @Column(name = "BOARD_CONTENT")
  String memberClearRoom;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "BOARD_CREATED_DATE")
  private LocalDateTime createdDate;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "BOARD_LAST_MODIFIED_DATE")
  private LocalDateTime lastModifiedDate;
}
