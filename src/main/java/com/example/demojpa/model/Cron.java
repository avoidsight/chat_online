package com.example.demojpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: zp
 * @Date: 2019-09-27 17:49
 * @Description:
 */
@Entity
@Data
public class Cron {
    @Id
    @Column
    private String name;

    @Column
    private String cron;
}
