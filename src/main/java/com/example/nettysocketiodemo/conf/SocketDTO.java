package com.example.nettysocketiodemo.conf;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SocketDTO
 *
 * @author chenqi
 * @date 2023/1/30 18:12
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "socketio")
public class SocketDTO implements Serializable {

  /**
   * url
   */
  private String url;

  /**
   * 端口
   */
  private Integer port;
  /**
   *  socket连接数大小（如只监听一个端口boss线程组为1即可）
   */
  private Integer bossCount;
  /**
   * 连接数大小
   */
  private Integer workCount;
  /**
   * 允许客户请求
   */
  private Boolean allowCustomRequests;
  /**
   * 协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间
   */
  private Long upgradeTimeout;

  /**
   * Ping消息超时时间(毫秒)，默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
   */
  private Long pingTimeout;

  /**
   * Ping消息间隔(毫秒)，默认25秒。客户端向服务器发送一条心跳消息间隔
   */
  private Long pingInterval;
  /**
   * 设置HTTP交互最大内容长度
   */
  private Long maxHttpContentLength;
  /**
   * 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
   */
  private Long maxFramePayloadLength;

}
