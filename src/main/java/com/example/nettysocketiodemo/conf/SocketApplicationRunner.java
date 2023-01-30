package com.example.nettysocketiodemo.conf;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SocketCommandLineRunner
 *
 * @author chenqi
 * @date 2023/1/30 18:24
 * @description
 */
@Slf4j
@Order(1)
@Component
public class SocketApplicationRunner implements ApplicationRunner {

  private final SocketIOServer socketIOServer;

  @Autowired
  public SocketApplicationRunner(SocketIOServer socketIOServer) {
    this.socketIOServer = socketIOServer;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    socketIOServer.start();
    log.info("socket start！！！");
  }
}

