package com.maze.memory.config;

import com.maze.memory.repository.MemberRepository;
import com.maze.memory.repository.impl.JpaMemberRepository;
import com.maze.memory.service.MemberService;
import com.maze.memory.service.impl.MyUserDetailsService;
import com.maze.memory.service.impl.UserDetailsServiceImpl;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class CommonConfig {

  private final DataSource dataSource;

  private final EntityManager em;

  public CommonConfig(DataSource dataSource, EntityManager em) {
    this.dataSource = dataSource;
    this.em = em;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    return new UserDetailsServiceImpl(memberRepository());
//  }

  @Bean
  public MyUserDetailsService myUserDetailsService() {
    return new MyUserDetailsService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new JpaMemberRepository(em);
  }
}