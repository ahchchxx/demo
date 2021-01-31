package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example")
// @EnableAspectJAutoProxy//开启spring注解aop配置的支持
public class SpringConfiguration {
}
