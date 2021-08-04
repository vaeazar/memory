package com.maze.memory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@Table(name = "MEMBER_INFO", schema = "memory")
public class MemberInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "MEMBER_ID")
  String memberID;
  @Column(name = "MEMBER_PW")
  String memberPW;
  @Column(name = "MEMBER_SALT")
  String memberSalt;
  @Column(name = "MEMBER_CLEAR_ROOM")
  String memberClearRoom;
  @Column(name = "MEMBER_RECENT_ROOM")
  String memberRecentRoom;
  @Column(name = "MEMBER_ROLE")
  String memberRole;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @CreationTimestamp
  @Column(name = "MEMBER_CREATED_DATE")
  private ZonedDateTime createdDate;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @UpdateTimestamp
  @Column(name = "MEMBER_LAST_MODIFIED_DATE")
  private ZonedDateTime lastModifiedDate;

  public MemberInfo() {
    this.memberRole = "member";
    this.memberClearRoom = "0";
    this.memberRecentRoom = "0";
  }

  public boolean IDAndPWBlank() {
    boolean idCheck = StringUtils.isBlank(memberID);
    boolean pwCheck = StringUtils.isBlank(memberPW);
    return idCheck && pwCheck;
  }
}
