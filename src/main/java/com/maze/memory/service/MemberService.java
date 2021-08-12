package com.maze.memory.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maze.memory.domain.MemberInfo;
import com.maze.memory.repository.MemberRepository;
import com.maze.memory.utils.CommonUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public JSONObject join(HashMap<String, Object> params) {

    JSONObject resultJson = new JSONObject();
    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      MemberInfo memberInfo = mapper.convertValue(params, MemberInfo.class);
      if (memberInfo.IDAndPWBlank()) {
        resultJson.put("resultFlag","fail");
        resultJson.put("reason","empty");
        return resultJson;
      }
      CommonUtils.pwChanger(memberInfo, null);

      //중복 회원 검증
      if (invalidateDuplicateMember(memberInfo).get()) {
        resultJson.put("resultFlag","fail");
        resultJson.put("reason","duplicate");
        return resultJson;
      }
      memberRepository.save(memberInfo);

      resultJson.put("resultFlag","complete");
      resultJson.put("memberID",memberInfo.getId());
    } catch (Exception e) {
      resultJson.put("resultFlag","fail");
    }
    return resultJson;
  }

  public boolean loginCheck(HttpSession session, HashMap<String, Object> params, JSONObject resultJson) {

    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      MemberInfo memberInfo = mapper.convertValue(params, MemberInfo.class);
      if (memberInfo.IDAndPWBlank()) {
        resultJson.put("resultFlag","fail");
        resultJson.put("reason","empty");
      }
      String memberSalt = memberRepository.findMemberSaltByMemberID(memberInfo.getMemberID());
      memberInfo.setMemberSalt(memberSalt);
      CommonUtils.pwChanger(memberInfo, null);
      Optional<MemberInfo> resultMember = findOneByMemberIDAndPW(memberInfo.getMemberID(),memberInfo.getMemberPW());
      if (resultMember.isPresent()) {
        resultJson.put("resultFlag","complete");
        resultJson.put("memberID",memberInfo.getMemberID());
        session.setAttribute(memberInfo.getMemberID(),true);
      } else {
        resultJson.put("resultFlag","wrongMember");
      }
    } catch (Exception e) {
      resultJson.put("resultFlag","fail");
    }
    return false;
  }

  public void jwtLoginCheck(MemberInfo memberInfo) {
    try {
      String memberSalt = memberRepository.findMemberSaltByMemberID(memberInfo.getMemberID());
      memberInfo.setMemberSalt(memberSalt);
      CommonUtils.pwChanger(memberInfo, null);
      Optional<MemberInfo> resultMember = findOneByMemberIDAndPW(memberInfo.getMemberID(),memberInfo.getMemberPW());
    } catch (Exception e) {
      log.error("jwtLoginCheckError::{}" + e);
    }
  }

  public boolean sessionCheck(HttpSession session, HashMap<String, Object> params, JSONObject resultJson) {

    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      MemberInfo memberInfo = mapper.convertValue(params, MemberInfo.class);
      if (memberInfo.IDAndPWBlank()) {
        resultJson.put("resultFlag","fail");
        resultJson.put("reason","empty");
      }
      Optional<MemberInfo> resultMember = findOneByMemberIDAndPW(memberInfo.getMemberID(),memberInfo.getMemberPW());
      if (resultMember.isPresent()) {
        resultJson.put("resultFlag","complete");
        resultJson.put("memberID",memberInfo.getMemberID());
        session.setAttribute(memberInfo.getMemberID(),true);
      }
    } catch (Exception e) {
      resultJson.put("resultFlag","fail");
    }
    return false;
  }

  private AtomicBoolean invalidateDuplicateMember(MemberInfo memberInfo) {
    AtomicBoolean validate = new AtomicBoolean(false);
    memberRepository.findByMemberID(memberInfo.getMemberID())
        .ifPresent(m -> {
          validate.set(true);
          log.info("이미 존재하는 회원입니다.");
        });
    return validate;
  }

  /**
   * 전체 회원 조회
   */

  public List<MemberInfo> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<MemberInfo> findOneById(Long memberId) {
    return memberRepository.findById(memberId);
  }

  public Optional<MemberInfo> findOneByMemberID(String memberID) {
    return memberRepository.findByMemberID(memberID);
  }

  public Optional<MemberInfo> findOneByMemberIDAndPW(String memberID, String memberPW) {
    return memberRepository.findByMemberIDAndPW(memberID, memberPW);
  }

}
