package com.gupaoedu.mybatis.plugin;

/**
 * @Author: xcao
 * @Description:
 * @Date:Create in 16:59 2018/4/10
 * @Modified By:
 */
public interface MyInterceptor {

    Object intercept(MyInvocation invocation) throws Throwable;

    Object plugin(Object target) throws Exception;

//    void setProperties(Properties properties);
}
