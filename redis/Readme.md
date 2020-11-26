
## Redis 样例工程

可以作为客户端的中间件

· Jedis

· Redisson

· RedisTemplate 

org.springframework.data.redis.core.RedisTemplate


### 连接池测试失败：

~正常 java类的实例化和 spring bean的实例化顺序，有一些不一样~


## redislock 
通过注解对方法加锁，还涉及反射、代理的相关知识。

注意学习里面的：

1，java.util.concurrent.CountDownLatch，控制并发

```java
CountDownLatch count = new CountDownLatch();
// 等待在一个信号量上，挂起
count.await();

// 主线程释放开始信号量，并等待结束信号量
// 这样做保证1000个线程做到完全同时执行，保证测试的正确性
count.countDown(); 
```

2，操作 redis 封装的工具方法

com.example.redislock.RedisClient

## Redisson
入口请看 RedissonTest 单元测试

1，RedissonConfig 的配置

分为：单点、哨兵、集群等多种分布式场景

2，实现分布式锁

基于 RedissonClient 实例的 lock() 和 unlock()方法



