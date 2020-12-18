package com.example;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/send")
    public String send(String msg) {
        rocketMQTemplate.convertAndSend("springboot-mq", msg);
        return "send success";
    }

}
