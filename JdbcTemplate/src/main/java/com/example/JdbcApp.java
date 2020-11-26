package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class JdbcApp {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApp.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/jdbc")
    public List jdbc() {
        String sqlStr = "select * from dict_city limit 10";
//        List<DictCity> dictCities = jdbcTemplate.queryForList(sqlStr, DictCity.class);
        List<Map<String, Object>> dictCities = jdbcTemplate.queryForList(sqlStr);

        return dictCities;
    }

}
