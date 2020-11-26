package com.etsoft.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "com.etsoft")
public class AProperties {
    private String name = "a-1";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
