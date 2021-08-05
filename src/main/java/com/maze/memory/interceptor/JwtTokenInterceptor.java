//package com.maze.memory.interceptor;
//
//import com.maze.memory.constants.AuthConstants;
//import com.maze.memory.utils.TokenUtils;
//import java.io.IOException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Log4j2
//public class JwtTokenInterceptor implements HandlerInterceptor {
//
//  @Override
//  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
//    final String header = request.getHeader(AuthConstants.AUTH_HEADER);
//
//    if (header != null) {
//      final String token = TokenUtils.getTokenFromHeader(header);
//      if (TokenUtils.isValidToken(token)) {
//        return true;
//      }
//    }
//    response.sendRedirect("/");
//    return false;
//
//  }
//
//}
