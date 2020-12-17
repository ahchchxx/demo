package com.gupaoedu.mybatis;

import com.gupaoedu.mybatis.executor.Executor;
import com.gupaoedu.mybatis.my.Test;
import com.gupaoedu.mybatis.config.GpConfiguration;
import com.gupaoedu.mybatis.executor.ExecutorFactory;
import com.gupaoedu.mybatis.session.GpSqlSession;
import com.gupaoedu.mybatis.my.TestMapper;

/**
 * Created by James on 2017-07-05.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class BootStrap {
    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws Exception {
        GpConfiguration configuration = new GpConfiguration();
        configuration.setScanPath("com.gupaoedu.mybatis.config.mappers"); // set scan path
        configuration.build(); // config plugging chain

        // get executor instance by Factory Design Pattern
        Executor executor = ExecutorFactory.get(ExecutorFactory.ExecutorType.SIMPLE.name(), configuration);
        GpSqlSession sqlSession = new GpSqlSession(configuration, executor);

        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);

        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}
