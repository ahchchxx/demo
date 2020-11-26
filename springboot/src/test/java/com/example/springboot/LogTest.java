package com.example.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){
        logger.trace("trace log");

        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；
    }
}
