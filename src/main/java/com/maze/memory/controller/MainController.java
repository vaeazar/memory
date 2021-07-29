package com.maze.memory.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class MainController {

  @RequestMapping("/")
  public ModelAndView Index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }
  @RequestMapping("memberRegister")
  public ModelAndView memberRegister() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("memberRegister");
    return mv;
  }
  @RequestMapping("board")
  public ModelAndView board() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("board");
    return mv;
  }
  @RequestMapping("test")
  public ModelAndView test() {
    log.info("유입 확인");
    ModelAndView mv = new ModelAndView();
    mv.setViewName("test");
    return mv;
  }
  @RequestMapping("outTest")
  @ResponseBody
  public String outTest() {
    log.info("이탈 확인");
    return "";
  }
}
