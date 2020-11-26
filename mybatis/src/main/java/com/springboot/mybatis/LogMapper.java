package com.springboot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select name, ip from t_log limit 100")
    public List<LogInfo> getAll();

    // @Select("select name, ip from t_log where name like '%#{}%'")
    public List<LogInfo> getByName(@Param("name") String name);

}
