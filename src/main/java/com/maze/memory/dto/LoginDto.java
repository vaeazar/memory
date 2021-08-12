package com.maze.memory.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

  @NotNull
  @Size(min = 3, max = 50)
  private String memberID;

  @NotNull
  @Size(min = 3, max = 100)
  private String memberPW;
}
