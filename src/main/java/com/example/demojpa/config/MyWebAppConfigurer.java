package com.example.demojpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: zp
 * @Date: 2019-10-30 18:01
 * @Description:
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Value("${user.avatar.url}")
    String avatarUrl;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatars/**").addResourceLocations("file://"+avatarUrl);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String pwd = "123456";
        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(pwd.getBytes());

        String newPwd = new BigInteger(1,md.digest()).toString();
        String newPwd1 = new BigInteger(1,md.digest()).toString(16);
        System.out.println("newPwd = " + newPwd);
        System.out.println("newPwd1 = " + newPwd1);
    }
}
