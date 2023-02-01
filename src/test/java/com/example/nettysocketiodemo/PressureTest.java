package com.example.nettysocketiodemo;

import io.socket.client.IO;
import io.socket.client.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;

/**
 * Test
 *
 * @author chenqi
 * @date 2023/2/1 11:50
 * @description
 */

public class PressureTest {

  @SneakyThrows
  public static void main(String[] args) {

    ExecutorService executorService = Executors.newFixedThreadPool(20);

    IntStream.rangeClosed(1, 5000).forEach((b) -> {
      String preLog = "client 【 " + b;
      Thread thread = new Thread(() -> {
        String url = "http://127.0.0.1:8007?params=" + b;
        try {
          IO.Options options = new IO.Options();
          options.transports = new String[] {"websocket"};
          //失败重试次数
          options.reconnectionAttempts = 10;
          //失败重连的时间间隔
          options.reconnectionDelay = 1000;
          //连接超时时间(ms)
          options.timeout = 5000;
          final Socket socket = IO.socket(url, options);
          //监听自定义msg事件
          socket.on("get", (a) -> System.out.println(preLog + "】: 收到msg->" + Arrays.toString(a)));
          socket.on(Socket.EVENT_CONNECT, objects -> {
            socket.emit("sub", preLog + "订阅时发起订阅消息");
            System.out.println(preLog + "连接成功");
          });
          socket.on(Socket.EVENT_CONNECTING, (a) -> System.out.println(preLog + "连接中"));
          socket.on(Socket.EVENT_CONNECT_TIMEOUT, (a) -> System.out.println(preLog + "连接超时"));
          socket.on(Socket.EVENT_CONNECT_ERROR, (a) -> System.out.println(preLog + "连接失败"));
          socket.connect();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
      executorService.submit(thread);
    });
    System.in.read();
  }


}
