package com.example.springboot.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AcceptException {
//    Logger logger = LoggerFactory.getLogger(AcceptException.class);

//    catch all Exception
    @ExceptionHandler(Exception.class)
    public RetBean exceptionHandle(Exception ex) {
//        System.out.println("com.example.springboot.config.AcceptException: " + ex.getMessage());
//        return RetBean.fail(ex.getMessage());
        ex.printStackTrace();
        return RetBean.fail("backend error");
    }

    @ExceptionHandler(CustomException.class)
    public RetBean exceptionHandle(CustomException ex) {
        System.out.println(ex.getMessage());
        return new RetBean(ex.codeMsg.getCode(), ex.codeMsg.getMsg(), "");
    }

}
