package com.maze.memory.utils;

import com.maze.memory.domain.MemberInfo;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import org.junit.platform.commons.util.StringUtils;

public class CommonUtils {

  public static void pwChanger(MemberInfo memberInfo) {
    String raw = memberInfo.getMemberPW();

    try {
      SecureRandom random = null;
      random = SecureRandom.getInstance("SHA1PRNG");
      byte[] bytes = new byte[16];
      random.nextBytes(bytes);
      String salt = new String(Base64.getEncoder().encode(bytes));
      if (StringUtils.isNotBlank(memberInfo.getMemberSalt())) {
        salt = memberInfo.getMemberSalt();
      } else {
        memberInfo.setMemberSalt(salt);
      }

      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt.getBytes());
      md.update(raw.getBytes());
      String hex = String.format("%064x", new BigInteger(1, md.digest()));

      memberInfo.setMemberPW(hex);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }
}
