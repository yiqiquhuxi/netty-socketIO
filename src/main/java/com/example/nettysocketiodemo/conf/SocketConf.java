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
    configuration.setBossThreads(socketDTO.getBossCount());
    //连接数大小
    configuration.setWorkerThreads(socketDTO.getWorkCount());
    // 允许客户请求
    configuration.setAllowCustomRequests(socketDTO.getAllowCustomRequests());
    // 协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间
    configuration.setUpgradeTimeout(socketDTO.getUpgradeTimeout().intValue());
    // Ping消息超时时间(毫秒)，默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
    configuration.setPingTimeout(socketDTO.getPingTimeout().intValue());
    // Ping消息间隔(毫秒)，默认25秒。客户端向服务器发送一条心跳消息间隔
    configuration.setPingInterval(socketDTO.getPingInterval().intValue());
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
