package com.gupaoedu.mybatis.plugin;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface MySignature {
    Class<?> type();

    String method();

    Class<?>[] args();
}