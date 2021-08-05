package com.maze.memory.utils;

import com.maze.memory.constants.UserRole;
import com.maze.memory.domain.MemberInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtils {

  public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
  public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

  final static public String ACCESS_TOKEN_NAME = "accessToken";
  final static public String REFRESH_TOKEN_NAME = "refreshToken";

  private static final String secretKey = "ThisIsA_SecretKeyForJwtExample";

  public static String generateJwtToken(MemberInfo memberInfo) {
    JwtBuilder builder = Jwts.builder().setSubject(memberInfo.getMemberID()).setHeader(createHeader()).setClaims(createClaims(memberInfo)).setExpiration(createExpireDateForOneYear())
        .signWith(SignatureAlgorithm.HS256, createSigningKey());
    return builder.compact();
  }

  public static boolean isValidToken(String token) {
    try {
      Claims claims = getClaimsFormToken(token);
      log.info("expireTime :" + claims.getExpiration());
      log.info("memberID :" + claims.get("memberID"));
      log.info("memberROle :" + claims.get("memberROle"));
      return true;
    } catch (ExpiredJwtException exception) {
      log.error("Token Expired");
      return false;
    } catch (JwtException exception) {
      log.error("Token Tampered");
      return false;
    } catch (NullPointerException exception) {
      log.error("Token is null");
      return false;
    }
  }

  public static String getTokenFromHeader(String header) {
    return header.split(" ")[1];
  }

  private static Date createExpireDateForOneYear() {
  // 토큰 만료시간은 30일으로 설정
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, 30);
    return c.getTime();
  }

  private static Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "JWT");
    header.put("alg", "HS256");
    header.put("regDate", System.currentTimeMillis());
    return header;
  }

  private static Map<String, Object> createClaims(MemberInfo memberInfo) {
  // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
    Map<String, Object> claims = new HashMap<>();
    claims.put("memberID", memberInfo.getMemberID());
    claims.put("memberROle", memberInfo.getMemberRole());
    return claims;
  }

  private static Key createSigningKey() {
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
    return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
  }

  private static Claims getClaimsFormToken(String token) {
    return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
  }

  private static String getUserEmailFromToken(String token) {
    Claims claims = getClaimsFormToken(token);
    return (String) claims.get("memberID");
  }

  private static UserRole getRoleFromToken(String token) {
    Claims claims = getClaimsFormToken(token);
    return (UserRole) claims.get("memberRole");
  }
}

