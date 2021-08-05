//package com.maze.memory.config;
//
//import com.maze.memory.interceptor.JwtTokenInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMVCConfig implements WebMvcConfigurer {
//
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    //인터셉터 추가 전체 user 경로 검색
//    registry.addInterceptor(jwtTokenInterceptor())
//        .addPathPatterns("/admin/**","/user/**");
//  }
//
//  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/", "classpath:/public/", "classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/",
//      "classpath:/META-INF/resources/webjars/"};
//
//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) { // /에 해당하는 url mapping을 /common/test로 forward한다.
//
//    registry.addViewController("/").setViewName("forward:/index"); // 우선순위를 가장 높게 잡는다.
//    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//  }
//
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//    registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//  }
//
//  @Bean
//  public JwtTokenInterceptor jwtTokenInterceptor() {
//    return new JwtTokenInterceptor();
//  }
//}
//
//
