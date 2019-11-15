package com.example.demojpa.exception;

/**
 * @author: zp
 * @Date: 2019-09-02 15:02
 * @Description:
 */
abstract class AbstractCustomException extends Exception{
    /**
     *
     * @return 错误提示
     */
    abstract String getMsg();
}
