package com.example.nettysocketiodemo.controller;

import com.example.nettysocketiodemo.service.MessageEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 *
 * @author chenqi
 * @date 2023/1/30 18:56
 * @description
 */
@RestController
@RequestMapping("demo")
public class Controller {

  @Autowired
  private MessageEventHandler messageEventHandler;

  @GetMapping("send")
  public <T> boolean send(String msg){
    return messageEventHandler.sendMsg("get",msg);
  }

}
