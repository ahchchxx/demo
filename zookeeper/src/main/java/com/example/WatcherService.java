package com.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WatcherService implements Watcher {
//    private static final Logger logger = LoggerFactory.getLogger(WatcherService.class);
    public static class logger {
        public static void info(String a, Object b) {
            System.out.println(a + String.valueOf(b));
        }
    }
    @Override
    public void process(WatchedEvent event) {
        logger.info("【Watcher监听事件】={}", event.getState());
        logger.info("【监听路径为】={}", event.getPath());

        //  三种监听类型： 创建，删除，更新
        logger.info("【监听的类型为】={}", event.getType());
    }
}
