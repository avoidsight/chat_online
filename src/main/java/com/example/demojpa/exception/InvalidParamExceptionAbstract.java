package com.example.demojpa.exception;

/**
 * @author: zp
 * @Date: 2019-09-04 16:35
 * @Description:
 */
public class InvalidParamExceptionAbstract extends AbstractCustomException {
    @Override
    public String getMsg() {
        return "无效参数类型！";
    }
}
