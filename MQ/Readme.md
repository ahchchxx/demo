
### RocketMQ 测试和学习案例

#### 一、整合在 SpringBoot 里的案例

- com.example
    - app
    - consumer
    - producer

#### 二、基于源码的案例
- com.example.MQDev
    - NameServer
    - Broker
    - Consumer
    - Producer

#### Netty in RocketMQ 

` 
NameServer is using netty as a ServerProvider
`

##### NameServer 端
NamesrvController 里面，注册了请求处理器 DefaultRequestProcessor


##### Broker 端
BrokerController 里面，通过线程池定时调用BrokerOuterAPI，注册 broker
