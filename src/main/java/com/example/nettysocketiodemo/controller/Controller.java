package com.example.nettysocketiodemo.controller;

import com.example.nettysocketiodemo.service.MessageEventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
@RequestMapping("demo")
public class Controller {

  @Autowired
  private MessageEventHandler messageEventHandler;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping("send")
  public <T> boolean send(String msg) {
    return messageEventHandler.sendMsg("get", msg);
  }

  /**
   * 发消息至mq
   *
   * @param msg
   * @param <T>
   * @return
   */
  @GetMapping("sendMq")
  public <T> String sendMq(String msg) {
    rabbitTemplate.convertAndSend("socketIO-fanout-exchange", null, msg);
    return "success";
  }


  @GetMapping("size")
  public <T> String size() {
    int size = MessageEventHandler.set.size();
    return "连接数量: " + size;
  }


}
