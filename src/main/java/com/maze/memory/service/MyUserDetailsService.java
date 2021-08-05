package com.maze.memory.service.impl;

import com.maze.memory.domain.MemberInfo;
import com.maze.memory.domain.SecurityMemberInfo;
import com.maze.memory.repository.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Autowired
  public MyUserDetailsService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException {

    Optional<MemberInfo> member = memberRepository.findByMemberID(memberID);
    MemberInfo memberInfo = new MemberInfo();
    if(!member.isPresent()){
      throw new UsernameNotFoundException(memberID + " : 사용자 존재하지 않음");
    } else {
      memberInfo = member.get();
    }

    return new SecurityMemberInfo(memberInfo);
  }
}
