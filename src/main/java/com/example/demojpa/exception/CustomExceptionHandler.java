package com.example.demojpa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zp
 * @Date: 2019-09-04 17:49
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(AbstractCustomException.class)
    @ResponseBody
    String handle(Exception ex, HttpServletRequest request){
//        Map map = Maps.newHashMap();
//        AbstractCustomException ace = (AbstractCustomException)ex;
//        map.put("error",ace.getMsg());
//        return map;
        log.info("窝窝头，一块钱四个，嘿嘿。");
        return ((AbstractCustomException)ex).getMsg();
    }

}
