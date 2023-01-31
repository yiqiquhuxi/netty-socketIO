package com.example.nettysocketiodemo.listener;

import com.example.nettysocketiodemo.service.MessageEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConsumerA
 *
 * @author chenqi
 * @date 2023/1/29 16:46
 * @description
 */
@Slf4j
@Component
@RabbitListener(queues = {"3-topic-socket-queue"})
public class ConsumerA {

  @Autowired
  private MessageEventHandler messageEventHandler;

  @RabbitHandler
  public void getMsg(String message) {
    log.info("C 接收到的数据,{}", message);
    messageEventHandler.sendMsg("get", message);
    log.info("mq 【{}】接收到消息，并且群发消息 【{}】至客户端", "3-topic-socket-queue", message);
  }


}
