package com.maze.memory.dto;

import lombok.Data;

@Data
public class TokenDto {

  public TokenDto(String token) {
    this.token = token;
  }

  String token;
}
