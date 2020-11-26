package com.springboot.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogMapper logMapper;

    @RequestMapping("/all")
    public List<LogInfo> getAll() {
        return logMapper.getAll();
    }

    @RequestMapping("/getByName")
    public List<LogInfo> getByName(@RequestParam String name) {
        return logMapper.getByName(name);
    }
}
