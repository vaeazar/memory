package com.maze.memory.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maze.memory.domain.MemberInfo;
import com.maze.memory.domain.response.ResponseFormat;
import com.maze.memory.jwt.JwtFilter;
import com.maze.memory.jwt.TokenProvider;
import com.maze.memory.service.MemberService;
import com.maze.memory.utils.CookieUtils;
import com.maze.memory.utils.ResponseUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final MemberService memberService;

  public AuthController(TokenProvider tokenProvider
      , AuthenticationManagerBuilder authenticationManagerBuilder
      , MemberService memberService) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.memberService = memberService;
  }

  @PostMapping("authenticate")
  public ResponseEntity<ResponseFormat> authorize(
      HttpServletResponse response, @RequestParam HashMap<String, Object> params
  ) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MemberInfo memberInfo = mapper.convertValue(params, MemberInfo.class);
    if (!memberService.jwtLoginCheckBoolean(memberInfo)) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    log.info("login요청");
    log.info("memberInfO" + ObjectUtils.isEmpty(memberInfo));
    log.info("memberInfO" + memberInfo);
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(memberInfo.getMemberID(), memberInfo.getMemberPW());

    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.createToken(authentication );

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    log.info("jwtToken:::" + jwt);
    Map<String, Object> data = new HashMap<>();
    data.put("jwtToken", jwt);

    Cookie accessToken = CookieUtils.createCookie("accessToken", jwt);
    Cookie refreshToken = CookieUtils.createCookie("refreshToken", jwt);
    //redisUtil.setDataExpire(refreshJwt, memberInfo.getMemberID(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
    response.addCookie(accessToken);
    response.addCookie(refreshToken);
    return ResponseUtils.getResponseOk(data);
    //return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }
}
