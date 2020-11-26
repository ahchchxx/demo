package com.example.springboot.config;

import com.example.springboot.config.CustomException;
import com.example.springboot.config.ErrorCodeMsg;
import com.example.springboot.config.RetBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {

//    @RequestMapping("test")
//    public String test(@RequestParam(required = false, defaultValue = "") String param) {
//        int i = 1/0;
//        System.out.println("Hello world" + param);
//        return "Hello world" + param;
//    }
//
//    @RequestMapping("customExcption")
//    public RetBean testEx() throws CustomException {
//        if ("a" == "b"){
//            return RetBean.success("");
//        } else if (1==2) {
//            return RetBean.fail("");
//        }
//        throw new CustomException(ErrorCodeMsg.ParamError);
//    }

}
