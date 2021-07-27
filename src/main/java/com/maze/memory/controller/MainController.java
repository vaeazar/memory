package com.maze.memory.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
