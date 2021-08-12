package com.maze.memory.controller;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.dto.TokenDto;
import com.maze.memory.jwt.JwtFilter;
import com.maze.memory.jwt.TokenProvider;
import com.maze.memory.service.MemberService;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<TokenDto> authorize(
    @Valid @RequestBody MemberInfo requetMemberInfo
  ) {
    MemberInfo memberInfo = requetMemberInfo;
    memberService.jwtLoginCheck(memberInfo);
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

    return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }
}
