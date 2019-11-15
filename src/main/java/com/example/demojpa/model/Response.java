package com.example.demojpa.model;

import lombok.Data;

/**
 * @author: zp
 * @Date: 2019-10-10 15:22
 * @Description:
 */
@Data
public class Response<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 请求成功时返回的对象
     */
    private T data;

    /**
     * 提示信息
     */
    private String msg;

}
