package com.gupaoedu.mybatis.config.mappers;

import com.gupaoedu.mybatis.my.Test;

public interface TestMapper { //com.gupaoedu.mybatis.gp.config.mappers.TestMapper
    Test selectByPrimaryKey(Integer userId);
}