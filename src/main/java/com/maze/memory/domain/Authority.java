package com.maze.memory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authority")
@Data
public class Authority {

  @Id
  @Column(name = "AUTHORITY_NAME", length = 50)
  private String authorityName;
}
