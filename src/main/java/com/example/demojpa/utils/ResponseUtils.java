package com.example.demojpa.utils;

import com.example.demojpa.model.Response;

/**
 * @author: zp
 * @Date: 2019-10-10 15:48
 * @Description:
 */
public class ResponseUtils {
    /**
     * 调用成功
     */
    private static final String SUCCESS = "调用成功！";

    public static Response success(Object obj){
        Response res = new Response();
        res.setCode(200);
        res.setData(obj);
        res.setMsg(SUCCESS);
        return res;
    }

    public static Response success(){
        return success(null);
    }

    public static Response error(Integer code, String msg){
        Response res = new Response();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

}
