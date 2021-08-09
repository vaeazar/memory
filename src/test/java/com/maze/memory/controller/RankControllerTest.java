package com.maze.memory.controller;

import com.maze.memory.domain.ClearInfo;
import com.maze.memory.service.RankService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@WebMvcTest(RankController.class)
@AutoConfigureRestDocs
class RankControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RankService rankService;

  @Test
  void getClearTop10() throws Exception {
    // given
    List<ClearInfo> response = new ArrayList<>();
    for (int i = 1; i < 3; i++) {
      ClearInfo info = ClearInfo.builder()
        .id((long)i)
        .memberId("member" + i)
        .roomId("r" + i)
        .spendTime(1029305L)
        .createdDate(LocalDateTime.now())
        .build();

      response.add(info);
    }

    given(rankService.getAllClearTop10())
      .willReturn(response);

    // when
    ResultActions result = this.mockMvc.perform(
      get("/rank/clear/top10")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    );

    // then
    result.andExpect(status().isOk())
      .andDo(document("/rank-clear-top10"));
  }
}