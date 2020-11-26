package com.etsoft.service;

import com.etsoft.config.AProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class SayHelloImpl implements SayHello {

    @Autowired
    private AProperties aProperties;

    @Override
    public String sayHello() {
        return aProperties.getName();
    }

}
