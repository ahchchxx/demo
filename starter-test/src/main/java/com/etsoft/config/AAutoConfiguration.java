package com.etsoft.config;

import com.etsoft.service.SayHello;
import com.etsoft.service.SayHelloImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingClass
@EnableConfigurationProperties({AProperties.class})
// 配置文件里 com.etsoft.enable=true时，才会加载bean？
//@ConditionalOnProperty(prefix = "com.etsoft", name = "enabled", havingValue = "true")
public class AAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SayHello sayHello(){
        return new SayHelloImpl();
    }

}
