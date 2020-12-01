package com.example.asynchronous;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
// @Slf4j
public class TaskExecutorConfig {
    @Bean("localDbThreadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.setKeepAliveSeconds(100);
        taskExecutor.setThreadNamePrefix("LocalDbTaskThreadPool");
        taskExecutor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor executor) -> {
            if (!executor.isShutdown()) {
                try {
                    Thread.sleep(300);
                    executor.getQueue().put(r);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        });
        taskExecutor.initialize();

        return taskExecutor;
    }
}
