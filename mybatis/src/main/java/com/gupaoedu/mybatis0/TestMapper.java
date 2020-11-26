package com.gupaoedu.mybatis0;

import com.gupaoedu.mybatis.my.Test;

/**
 * Created by James on 2018-04-01.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}
