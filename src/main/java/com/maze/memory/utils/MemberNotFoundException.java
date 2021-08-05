package com.maze.memory.utils;

public class MemberNotFoundException extends RuntimeException {

  public MemberNotFoundException(String memberID) {
    super(memberID + " NotFoundException");
  }
}
