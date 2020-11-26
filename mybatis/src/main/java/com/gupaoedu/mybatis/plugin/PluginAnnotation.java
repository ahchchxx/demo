package com.gupaoedu.mybatis.plugin;

import org.apache.ibatis.plugin.Signature;

import java.lang.annotation.*;

/**
 * @Author:caoxiang
 * @Description:
 * @Date: Create in 21:34 2018/4/10
 * @Modified By;
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PluginAnnotation {

    Signature[] value();
}
