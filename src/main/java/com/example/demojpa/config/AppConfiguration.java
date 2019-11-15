package com.example.demojpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import redis.clients.jedis.*;

/**
 * @author: zp
 * @Date: 2019-09-18 10:03
 * @Description:
 */
@Configuration
public class AppConfiguration {


    @Bean
    public JedisPool jedisPool(){
        return new JedisPool("");
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("94.191.106.240");
        jedis.auth("zhaPeng213.");
        String value = jedis.get("huang");
        System.out.println("huang.gx = " + value);
    }
}
