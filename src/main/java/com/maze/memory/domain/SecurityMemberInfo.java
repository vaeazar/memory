package com.maze.memory.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityMemberInfo extends User {
  private static final long serialVersionUiD = 1L;

  public SecurityMemberInfo(MemberInfo memberInfo){
    super(memberInfo.getMemberID(),"{bcrypt}"+ memberInfo.getMemberPW(), AuthorityUtils.createAuthorityList(memberInfo.getMemberRole()));
  }
}
