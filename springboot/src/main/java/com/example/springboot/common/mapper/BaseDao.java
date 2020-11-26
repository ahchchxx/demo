package com.example.springboot.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

// 这个只是用于测试，dao层的继承关系
//      可以移回 mybatis-plus 包

public interface BaseDao<T> extends BaseMapper<T> {
}
