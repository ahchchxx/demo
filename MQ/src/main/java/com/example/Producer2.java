package com.example;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer2 {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/send2")
    public String send(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic("springboot-mq");
        message.setTags("tag1");
        message.setBody(msg.getBytes());

        rocketMQTemplate.getProducer().send(message);
        return "send success2";
    }

}
