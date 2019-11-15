package com.example.demojpa.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zp
 * @Date: 2019-08-22 18:50
 * @Description:
 */
//@FeignClient(name = "menmen2")
public interface FeignInterface {
    @GetMapping("/test")
    String succ();
}
