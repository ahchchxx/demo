package com.gupaoedu.mybatis0;

import com.gupaoedu.mybatis.my.Test;

public class app {
    public static void main(String[] args) {
        GPSqlSession sqlSession = new GPSqlSession(new GPConfiguration(), new GPSimpleExecutor());

        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(mapper.toString());
        Test test =  mapper.selectByPrimaryKey(1);

        System.out.println(test);
    }
}
