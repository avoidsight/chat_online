package com.example.demojpa;

import com.example.demojpa.consts.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author jiezhankeji
 */
@EnableConfigurationProperties({JwtProperties.class})
@SpringBootApplication
public class DemoJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }
}
