package com.example.demojpa.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zp
 * @Date: 2019-09-04 14:43
 * @Description:
 */
@Component
public class CustomerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 转化为 Json 形式传到前端,不然会 404
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        if(ex instanceof AbstractCustomException) {
            AbstractCustomException ce = (AbstractCustomException)ex;
            mv.addObject("error",ce.getMsg());
        }
        return mv;
    }
}
