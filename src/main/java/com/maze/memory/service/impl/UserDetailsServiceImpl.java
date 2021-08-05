package com.maze.memory.service.impl;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.domain.MyUserDetails;
import com.maze.memory.repository.MemberRepository;
import com.maze.memory.utils.MemberNotFoundException;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Autowired
  public UserDetailsServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException {
    return memberRepository.findByMemberID(memberID).map(m -> new MyUserDetails(m, Collections.singleton(
        new SimpleGrantedAuthority(m.getMemberRole())))).orElseThrow(() -> new MemberNotFoundException(memberID));
  }
}
