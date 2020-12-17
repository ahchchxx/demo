package com.gupaoedu.mybatis.my;

import com.gupaoedu.mybatis.executor.Executor;
import com.gupaoedu.mybatis.plugin.*;
import com.gupaoedu.mybatis.config.MapperRegistory.MapperData;

/**
 * 这个就是测试的插件类
 */
@PluginAnnotation({@MySignature(type = Executor.class, method = "query", args = {MapperData.class, Object.class})})
public class MyInterceptorTest implements MyInterceptor {
    @Override
    public Object intercept(MyInvocation invocation) throws Throwable {
        // MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        // System.out.println(String.format("plugin output sql = %s , param=%s", boundSql.getSql(), boundSql.getParameterObject()));
        System.out.println(invocation);
        System.out.println("MyTestPlugin==============intercept, specific functions changed in Plugin by AOP");
        return invocation.proceed(); // 最终执行所拦截的方法
    }

    @Override
    public Object plugin(Object target) throws Exception {
        System.out.println("MyTestPlugin------------plugin");// 执行 2次？
        // 通过代理模式，将所有用 PluginAnnotation 和 MySignature 注解的类都用 MyPlugin 类代理
        // 目的是实现 插件
        return MyPlugin.wrap(target, this);
    }
}
