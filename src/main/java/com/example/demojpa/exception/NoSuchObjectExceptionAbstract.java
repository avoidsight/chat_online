package com.example.demojpa.exception;

/**
 * @author: zp
 * @Date: 2019-09-04 16:40
 * @Description:
 */
public class NoSuchObjectExceptionAbstract extends AbstractCustomException {
    @Override
    String getMsg() {
        return "找不到对象! ";
    }
}
