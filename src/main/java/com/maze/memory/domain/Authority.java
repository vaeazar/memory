package com.maze.memory.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "member_authority")
@Data
public class Authority implements Serializable {

  @Id
  @Column(name = "MEMBER_ID", length = 50)
  private String memberID;

  @Column(name = "AUTHORITY_NAME", length = 50)
  private String authorityName;
}
