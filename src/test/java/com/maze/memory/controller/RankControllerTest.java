package com.maze.memory.controller;

import com.maze.memory.domain.ClearInfo;
import com.maze.memory.service.RankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RankController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class RankControllerTest {
  private MockMvc mockMvc;

  @MockBean
  private RankService rankService;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(
            documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint())
        )
        .build();
  }

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
      .andDo(document("rank-clear-top10",
        responseFields(
          fieldWithPath("code").description("?????? ??????"),
          fieldWithPath("message").description("?????? ??????"),
          fieldWithPath("data").description("?????? ?????????"),
          fieldWithPath("data.infos").description("????????? ?????? ??????"),
          fieldWithPath("data.infos[].id").description("????????? ?????? ?????????"),
          fieldWithPath("data.infos[].memberId").description("????????? ?????? ?????????"),
          fieldWithPath("data.infos[].roomId").description("????????? ??? ?????????"),
          fieldWithPath("data.infos[].spendTime").description("????????? ????????????(ms)"),
          fieldWithPath("data.infos[].createdDate").description("????????? ??????(?????? ??????)")
        )));
  }
}