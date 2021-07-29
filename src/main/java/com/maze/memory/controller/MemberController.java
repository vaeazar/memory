package com.maze.memory.controller;

import com.maze.memory.service.MemberService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  @RequestMapping("login.do")
  @ResponseBody
  public String memberLogin(HttpServletRequest request, @RequestParam HashMap<String, Object> params) {
    JSONObject resultString = new JSONObject();

    try {
      if (memberService.loginCheck(request.getSession(), params, resultString)) {
        resultString.put("resultFlag","wrongMember");
      } else {
      }
    } catch (Exception e) {
      e.printStackTrace();
      resultString.put("resultFlag","fail");
    }

    return resultString.toJSONString();
  }

  @RequestMapping("memberRegister.do")
  @ResponseBody
  public String memberRegister(@RequestParam HashMap<String, Object> params) {
    JSONObject memberListString = new JSONObject();

    try {
      memberListString = memberService.join(params);
    } catch (Exception e) {
      e.printStackTrace();
      memberListString.put("resultFlag","fail");
    }

    return memberListString.toJSONString();
  }
}
