package com.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;


@Component
public class ZookeeperConfig {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        // get response from zk server
                        countDownLatch.countDown();
                    }
                    // if (watchedEvent.getType() == )
                    logger.info("new event: " + watchedEvent.getPath());
                }
            });
            countDownLatch.await();
        } catch (Exception ex) {
            logger.error("zk error");
        }

        return zooKeeper;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
