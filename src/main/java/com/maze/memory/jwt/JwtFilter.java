package com.maze.memory.jwt;

import com.maze.memory.utils.CookieUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

  private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

  //public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String AUTHORIZATION_HEADER = "accessToken";

  private TokenProvider tokenProvider;

  @Autowired
  private CookieUtils cookieUtils;

  public JwtFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String jwt = resolveToken(httpServletRequest);
    String requestURI = httpServletRequest.getRequestURI();

    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
      Authentication authentication = tokenProvider.getAuthentication(jwt);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      logger.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",authentication.getName(), requestURI);
    } else {
      logger.info("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
    }

    chain.doFilter(request, response);
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    Cookie bearerCookie = CookieUtils.getCookie(request,AUTHORIZATION_HEADER);
    bearerToken = ObjectUtils.isEmpty(bearerCookie) ? "" : bearerCookie.getValue();
//    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//      return bearerToken.substring(7);
//    }
    if (StringUtils.hasText(bearerToken)) {
      return bearerToken;
    }
    return null;
  }
}
