# 工程简介
本项目为tedu netty-socketIO demo项目
> 技术栈（具体版本看pom文件）
>1. springboot
>2. netty-socketIO
>3. rabbitmq (满足分布式部署问题)


# 项目结构：
```
└── src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── nettysocketiodemo
│   │               ├── conf
│   │               ├── controller
│   │               ├── listener
│   │               └── service
│   └── resources
│       ├── static
│       │   └── demo
│       └── templates
└── test
└── java
└── com
└── example
└── nettysocketiodemo
```

# 使用步骤

- **客户端链接服务端**
  > 打开socektIO.html 文件，建立链接 （打开多个既建立多个链接）
- **服务端广播客户端（选择其一）：**
  > 1. get请求：http://localhost:8006/demo/send?msg=需要群发的消息
  > 2. get请求发送mq消息：http://localhost:8006/demo/sendMq?msg=需要群发的消息
- **查看链接数量**
  > get请求：http://localhost:8006/demo/size



# 延伸阅读

