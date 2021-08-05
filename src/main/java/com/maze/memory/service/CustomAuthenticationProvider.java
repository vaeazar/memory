package com.maze.memory.service;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.domain.MyUserDetails;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) {

    final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
    // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
    final String memberID = token.getName();
    final String memberPW = (String) token.getCredentials();
    // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
    final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(memberID);
    if (!passwordEncoder.matches(memberPW, userDetails.getMemberPW())) {
      throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, memberPW, userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}


