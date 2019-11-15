package com.example.demojpa.consts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: zp
 * @Date: 2019-11-15 16:16
 * @Description:
 */
@ConfigurationProperties(prefix = "token")
@Data
public class JwtProperties {
    /**
     * token过期时间，单位分钟
     */
    Integer tokenExpireTime;
    /**
     * 刷新Token过期时间，单位分钟
     */
    Integer refreshTokenExpireTime;
    /**
     * Shiro缓存有效期，单位分钟
     */
    Integer shiroCacheExpireTime;
    /**
     * token加密密钥
     */
    String secretKey;
}