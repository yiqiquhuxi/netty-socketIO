package com.example.nettysocketiodemo.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessageEventHandler
 *
 * @author chenqi
 * @date 2023/1/30 18:36
 * @description
 */
@Slf4j
@Service
public class MessageEventHandler {

  @Autowired
  public static SocketIOServer socketIoServer;

  public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

  @OnConnect
  public void onConnect(SocketIOClient client) {
    String params = client.getHandshakeData().getSingleUrlParam("params");
    log.info("{} 连接上,远程地址 {} 客户端sessionId {}", params, client.getRemoteAddress(), client.getSessionId());
    socketIOClientMap.put(params, client);
    int i = 1;

  }

  /**
   * 客户端断开连接
   *
   * @param client
   */
  @OnDisconnect
  public void onDisconnect(SocketIOClient client) {
    String params = client.getHandshakeData().getSingleUrlParam("params");
    log.info("{} 已断开", params);
    socketIOClientMap.remove(params);
  }

  /**
   * 客户端事件
   *
   * @param client
   * @param request
   * @param data
   */
  @OnEvent(value = "messageevent")
  public void onEvent(SocketIOClient client, AckRequest request, String data) {
    String params = client.getHandshakeData().getSingleUrlParam("params");
    log.info("{} 发来消息 {}", params, data);
  }

  // 广播消息
  public boolean sendMsg(String event, String message) {
    socketIOClientMap.values().parallelStream()
        .filter(Objects::nonNull)
        .forEach((a) -> {
          a.sendEvent(event, message);
        });
    return true;
  }


}
