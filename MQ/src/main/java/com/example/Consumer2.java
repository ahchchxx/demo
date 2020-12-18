package com.example;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "springboot-mq", consumerGroup = "springboot-mq-consumer-2",
        messageModel= MessageModel.BROADCASTING)
public class Consumer2 implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Receive message2：" + message);
    }
    
}
