package com.maze.memory.controller;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.service.MemberService;
import com.maze.memory.utils.CookieUtils;
import com.maze.memory.utils.JwtUtil;
import com.maze.memory.utils.RedisUtil;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties.SameSite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MemberController {

  private final MemberService memberService;

  private final JwtUtil jwtUtil;
  private final CookieUtils cookieUtils;
  private final RedisUtil redisUtil;

  @Autowired
  public MemberController(MemberService memberService, JwtUtil jwtUtil, CookieUtils cookieUtils, RedisUtil redisUtil) {
    this.memberService = memberService;
    this.jwtUtil = jwtUtil;
    this.cookieUtils = cookieUtils;
    this.redisUtil = redisUtil;
  }

  @RequestMapping("/common/login.do")
  @ResponseBody
  public String memberLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> params) {
    JSONObject resultString = new JSONObject();
    MemberInfo memberInfo = new MemberInfo();

    try {
      if (memberService.loginCheck(memberInfo, params, resultString)) {
        final String token = jwtUtil.generateToken(memberInfo);
        final String refreshJwt = jwtUtil.generateRefreshToken(memberInfo);
        Cookie accessToken = cookieUtils.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToken = cookieUtils.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
        //redisUtil.setDataExpire(refreshJwt, memberInfo.getMemberID(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        response.addCookie(accessToken);
        response.addCookie(refreshToken);
      } else {
        resultString.put("resultFlag","wrongMember");
      }
    } catch (Exception e) {
      e.printStackTrace();
      resultString.put("resultFlag","fail");
    }

    return resultString.toJSONString();
  }

  @RequestMapping("/common/memberRegister.do")
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
