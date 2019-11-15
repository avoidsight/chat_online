package com.example.demojpa.task;

import com.example.demojpa.utils.MailUtils;
import com.example.demojpa.utils.PingUtils;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zp
 * @Date: 2019-10-08 16:22:20
 * @Description:
 */
//@Configuration
//@EnableScheduling
public class TaskBasedInterface implements SchedulingConfigurer {

    /**
     * 每小时执行一次
     */
    private static String cron = "0 */1 * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(()-> {
            // 测试连接
            boolean connected = PingUtils.ping02("www.google.com");
            if(connected){
                try {
                    // 修改cron表达式，每天凌晨执行给我发邮件
                    cron = "0 0 0 * * ? *";
                    MailUtils.sendQQMail();
                    log("已成功ping通！");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log("ping不通");
        },cron);
    }

    public static void log(String message) {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String date = df.format(new Date(System.currentTimeMillis()));
        System.out.println(date + " "+message);
    }
}
