package com.example.MQDev;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("my_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        try {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest", // Topic
                    "TagA", // Tag
                    ("Hello RocketMQ").getBytes(RemotingHelper.DEFAULT_CHARSET) // Message body
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }

        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
