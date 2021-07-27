package com.maze.memory.utils;

import com.maze.memory.domain.MemberInfo;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class StringUtils {

  public static void pwChanger(MemberInfo memberInfo) {
    String raw = memberInfo.getMemberPW();

    try {
      SecureRandom random = null;
      random = SecureRandom.getInstance("SHA1PRNG");
      byte[] bytes = new byte[16];
      random.nextBytes(bytes);
      String salt = new String(Base64.getEncoder().encode(bytes));

      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt.getBytes());
      md.update(raw.getBytes());
      String hex = String.format("%064x", new BigInteger(1, md.digest()));

      memberInfo.setMemberPW(hex);
      memberInfo.setMemberSalt(salt);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }
}
