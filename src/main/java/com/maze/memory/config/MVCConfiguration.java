package com.maze.memory.config;


import com.maze.memory.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new Interceptor())
        .addPathPatterns("/*.do")
        .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/js/**");
  }

}
