package com.maze.memory.service;

import com.maze.memory.constants.AuthConstants;
import com.maze.memory.domain.MemberInfo;
import com.maze.memory.domain.MyUserDetails;
import com.maze.memory.utils.TokenUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
    final MemberInfo memberInfo = ((MyUserDetails) authentication.getPrincipal()).getMemberInfo();
    final String token = TokenUtils.generateJwtToken(memberInfo);
    response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
  }
}

