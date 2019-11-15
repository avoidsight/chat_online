package com.example.demojpa.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: zp
 * @Date: 2019-09-28 09:32
 * @Description:
 */
//@Configuration
@EnableScheduling
@EnableAsync
public class AsyncTask {

    @Async
    @Scheduled(cron = "*/4 * * * * ?")
    public void message(){
        System.out.println("menmen ni hao !"+ Thread.currentThread().getName());
    }

}