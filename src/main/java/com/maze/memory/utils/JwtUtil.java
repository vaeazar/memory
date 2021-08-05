package com.maze.memory.utils;

import com.maze.memory.domain.MemberInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
  //public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;
  public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60;

  final static public String ACCESS_TOKEN_NAME = "accessToken";
  final static public String REFRESH_TOKEN_NAME = "refreshToken";

  @Value("${spring.jwt.secret}")
  private String SECRET_KEY;

  private Key getSigningKey(String secretKey) {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Claims extractAllClaims(String token) throws ExpiredJwtException {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey(SECRET_KEY))
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String getMemberID(String token) {
    return extractAllClaims(token).get("memberID", String.class);
  }

  public String getMemberRole(String token) {
    return extractAllClaims(token).get("memberRole", String.class);
  }

  public Boolean isTokenExpired(String token) {
    final Date expiration = extractAllClaims(token).getExpiration();
    return expiration.before(new Date());
  }

  public String generateToken(MemberInfo memberInfo) {
    return doGenerateToken(memberInfo, TOKEN_VALIDATION_SECOND);
  }

  public String generateRefreshToken(MemberInfo memberInfo) {
    return doGenerateToken(memberInfo, REFRESH_TOKEN_VALIDATION_SECOND);
  }

  public String doGenerateToken(MemberInfo memberInfo, long expireTime) {

    Claims claims = Jwts.claims();
    claims.put("memberID", memberInfo.getMemberID());
    claims.put("memberRole", memberInfo.getMemberRole());

    String jwt = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expireTime))
        .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
        .compact();

    return jwt;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String memberID = getMemberID(token);

    return (memberID.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}
