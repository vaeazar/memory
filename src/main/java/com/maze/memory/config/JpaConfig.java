package com.maze.memory.config;

import com.maze.memory.repository.MemberRepository;
import com.maze.memory.repository.impl.JpaMemberRepository;
import com.maze.memory.service.MemberService;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class JpaConfig {

  private final DataSource dataSource;

  private final EntityManager em;

  public JpaConfig(DataSource dataSource, EntityManager em) {
    this.dataSource = dataSource;
    this.em = em;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository(), new BCryptPasswordEncoder());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new JpaMemberRepository(em);
  }
}