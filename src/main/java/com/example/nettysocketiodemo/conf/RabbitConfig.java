package com.example.nettysocketiodemo.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TopicConfiguration
 *
 * @author chenqi
 * @date 2023/1/29 16:16
 * @description
 */

@Configuration
public class RabbitConfig {


  @Bean
  public FanoutExchange fanoutExchange(){
    return ExchangeBuilder.fanoutExchange("socketIO-fanout-exchange").build();
  }

  @Bean
  public Queue queue1(){
    return QueueBuilder.durable("3-topic-socket-queue").build();
  }


  @Bean
  public Binding bindingAA(){
    return BindingBuilder.bind(queue1()).to(fanoutExchange());
  }



  @Bean
  public Queue queue2(){
    return QueueBuilder.durable("4-topic-socket-queue").build();
  }


  @Bean
  public Binding bindingBB(){
    return BindingBuilder.bind(queue2()).to(fanoutExchange());
  }






}
