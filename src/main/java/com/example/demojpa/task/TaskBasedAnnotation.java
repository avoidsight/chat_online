package com.example.demojpa.task;

import com.example.demojpa.utils.MailUtils;
import com.example.demojpa.utils.PingUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

/**
 * @author: zp
 * @Date: 2019-09-28 17:08
 * @Description:
 */

//@Configuration
@EnableScheduling
public class TaskBasedAnnotation {

    @Scheduled(cron = "0 */1 * * * ?")
    public void sayHello() throws MessagingException {
        boolean connected = PingUtils.ping02("www.google.com");
        if(connected){
            MailUtils.sendQQMail();
        }
    }
}
