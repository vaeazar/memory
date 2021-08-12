package com.maze.memory.service;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.repository.JwtRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  private final JwtRepository jwtRepository;

  public CustomUserDetailsService(JwtRepository jwtRepository) {
    this.jwtRepository = jwtRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jwtRepository.findOneWithMemberRoleByMemberID(username)
        .map(memberInfo -> createUser(username, memberInfo))
        .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
  }

  private User createUser(String username, MemberInfo memberInfo) {
//    if (!user.isActivated()) {
//      throw new RuntimeException(username + " -> 활성화 되어있지 않습니다.");
//    }
    List<GrantedAuthority> grantedAuthorities = memberInfo.getAuthorities().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
        .collect(Collectors.toList());
    return new User(memberInfo.getMemberID()
        ,memberInfo.getMemberPW()
        ,grantedAuthorities);
  }
}
