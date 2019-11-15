package com.example.demojpa.dao;

import com.example.demojpa.model.Cron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @author: ZhaPeng
 * @Date: 2019-07-18 09:55
 * @Description:
 */
public interface CronRepository extends Repository<Cron,Integer> {
    @Query(value = "select cron from cron limit 1",nativeQuery = true)
     String getCron();
}
