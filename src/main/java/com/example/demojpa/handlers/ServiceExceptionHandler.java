package com.example.demojpa.handlers;

import com.example.demojpa.exception.ServiceException;
import com.example.demojpa.model.Response;
import com.example.demojpa.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zp
 * @Date: 2019-10-10 15:11
 * @Description:
 */

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {
    private final Integer UNKNOW_ERROR_CODE = 500;

    private final String UNKNOW_ERROR_MSG = "未知错误,请联系管理员。";

    /**
     * ExceptionHandler 相当于controller的 RequestMapping
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return 响应对象
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Response handle(ServiceException se){
        return ResponseUtils.error(se.getCode(),se.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response handleOthers(RuntimeException re){
        log.error(re.getMessage());
        return ResponseUtils.error(UNKNOW_ERROR_CODE,UNKNOW_ERROR_MSG);
    }
}
