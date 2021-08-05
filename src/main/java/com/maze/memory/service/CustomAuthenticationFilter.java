package com.maze.memory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maze.memory.domain.MemberInfo;
import com.maze.memory.utils.InputNotFoundException;
import java.io.IOException;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
    final UsernamePasswordAuthenticationToken authRequest;
    try {
      final MemberInfo user = new ObjectMapper().readValue(request.getInputStream(), MemberInfo.class);
      authRequest = new UsernamePasswordAuthenticationToken(user.getMemberID(), user.getMemberPW());
    } catch (IOException exception) {
      throw new InputNotFoundException();
    }
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }
}

