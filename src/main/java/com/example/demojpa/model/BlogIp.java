package com.example.demojpa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zp
 * @Date: 2019-10-21 17:45:25
 * @Description:
 */
@Table(name = "blog_ip")
@Entity
@Data
public class BlogIp {
    private static AtomicInteger i = new AtomicInteger(0);
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id ;

    @Column
    @NotNull
    private String ip;

    @Column
    private String city;

    @Column
    private Integer rank;

    @Column
    @Email
    private String email;

}

