//package com.maze.memory.config;
//
//import com.maze.memory.service.CustomAuthenticationFilter;
//import com.maze.memory.service.CustomAuthenticationProvider;
//import com.maze.memory.service.CustomLoginSuccessHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private final UserDetailsService userDetailsService;
//
//
//  // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
//  @Override
//  public void configure(WebSecurity web) {
//
//    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//
//    http
//        .httpBasic().disable() // REST API만을 고려, 기본 설정 해제
//        .csrf().disable() // csrf 사용 X
//        // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
//        .authorizeRequests() // 요청에 대한 사용권한 체크
//        .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//        .antMatchers("/user/**").hasRole("ROLE_MEMBER")
//        .anyRequest().permitAll()
//        .and()
//        // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//        // form 기반의 로그인에 대해 비활성화 한다.
//        .formLogin()
//        .disable();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder bCryptPasswordEncoder() {
//
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//
//    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
//    customAuthenticationFilter.setFilterProcessesUrl("/user/login");
//    customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
//    customAuthenticationFilter.afterPropertiesSet();
//    return customAuthenticationFilter;
//  }
//
//  @Bean
//  public CustomLoginSuccessHandler customLoginSuccessHandler() {
//    return new CustomLoginSuccessHandler();
//  }
//
//  @Bean
//  public CustomAuthenticationProvider customAuthenticationProvider() {
//
//    return new CustomAuthenticationProvider(userDetailsService, bCryptPasswordEncoder());
//  }
//
//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
//
//    authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
//  }
//}
//
