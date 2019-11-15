package com.example.demojpa.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: zp
 * @Date: 2019-11-15 16:44
 * @Description:
 */
public class JwtToken implements AuthenticationToken {
    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}