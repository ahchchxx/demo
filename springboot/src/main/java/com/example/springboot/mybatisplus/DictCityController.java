package com.example.springboot.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.config.RetBean;
import com.example.springboot.mybatisplus.mapper.DictCityMapper;
import com.example.springboot.mybatisplus.mapper.DictCityMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict/city")
public class DictCityController {

    @Autowired
    DictCityMapper1 dictCityMapper;

    @RequestMapping("/all")
    public RetBean getAll() {
        List<DictCity> ret = dictCityMapper.selectList(null);
        return RetBean.success(ret);
    }

    @RequestMapping("/page")
    public RetBean getPage(@RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(defaultValue = "10") int pageSize, String name) {
        Page<DictCity> page = new Page<>(pageNo, pageSize);

//        QueryWrapper<DictCity> wrapper = new QueryWrapper();
//        wrapper.like("name", name);
//        wrapper.gt("id", 2);
//        Page<DictCity> dictCityPage = dictCityMapper.selectPage(page, wrapper);

        Page<DictCity> dictCityPage = dictCityMapper.selectPage(page, null);

        return RetBean.success(dictCityPage);
    }

    @RequestMapping("/single")
    public RetBean getSingle(int id) {
        DictCity dictCity = dictCityMapper.selectById(id);
        return RetBean.success(dictCity);
    }

}
