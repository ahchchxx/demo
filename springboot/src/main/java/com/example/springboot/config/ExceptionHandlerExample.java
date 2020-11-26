package com.example.springboot.config;

//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//// an example to handle exceptions in Spring-MVC project
//public class ExceptionHandlerExample implements HandlerExceptionResolver {
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res,
//                                         Object o, Exception e) {
//        PrintWriter out = getPrintWrite(res);
//        if (e instanceof CustomException) {
//            out.write("somethig to response" + e.getMessage());
//        } else {
//
//        }
//        return null;
//    }
//
//    private PrintWriter getPrintWrite(HttpServletResponse response) {
//        PrintWriter out = null;
//        try {
//            response.setHeader("Content-type", "text/html;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            out = response.getWriter();
//        } catch (IOException e) {
////            log.error("PrintWriter is exception", e);
//        }
//        return out;
//    }
//}
