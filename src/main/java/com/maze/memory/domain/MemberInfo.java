package com.maze.memory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

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

  public MemberInfo() {
    this.memberClearRoom = "0";
    this.memberRecentRoom = "0";
  }
}
