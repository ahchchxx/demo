package com.example.redisson;

import com.example.RedisConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class RedissonConfig {
    @Autowired
    RedisConfig redisConfig;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort());
        RedissonClient client = Redisson.create(config);

        return client;
    }

// 集群状态 的配置
//config.useClusterServers()
//        .setScanInterval(2000)//设置集群状态扫描时间
//        .setMasterConnectionPoolSize(10000)//设置连接数
//        .setSlaveConnectionPoolSize(10000)
//        .addNodeAddress(address)
//        //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
//        .setConnectTimeout(30000)
//        //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。默认:3000
//        .setReconnectionTimeout(10000)
//        //等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
//        .setTimeout(10000)
//        //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
//        .setRetryAttempts(5)
//        //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
//        .setRetryInterval(3000)

// 哨兵模式下的配置
//config.useSentinelServers()
//        .setMasterName(masterName)
//        .addSentinelAddress(address)
//        //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
//        .setConnectTimeout(30000)
//        //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。默认:3000
//        .setReconnectionTimeout(10000)
//        //等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
//        .setTimeout(10000)
//        //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
//        .setRetryAttempts(5)
//        //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
//        .setRetryInterval(3000)
}
