package com.example.nettysocketiodemo.conf;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SocketConf
 *
 * @author chenqi
 * @date 2023/1/30 18:17
 * @description
 */
@Configuration
public class SocketConf {

  @Autowired
  private SocketDTO socketDTO;

  @Bean
  public SocketIOServer socketIOServer() {
    com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
    configuration.setHostname(socketDTO.getUrl());
    configuration.setPort(socketDTO.getPort());
    // 鉴权
    configuration.setAuthorizationListener(new AuthorizationListener() {
      @Override
      public boolean isAuthorized(HandshakeData data) {
        //可以有逻辑，我这里没写
        return true;
      }
    });
    final SocketIOServer server = new SocketIOServer(configuration);
    return server;
  }

  @Bean
  public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
    return new SpringAnnotationScanner(socketServer);
  }

}
