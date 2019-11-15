package com.example.demojpa.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author: zp
 * @Date: 2019-09-12 11:54
 * @Description:
 */
public class FutureTaskUtil {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        // 热水器，2000ms后可使用热水
        Callable callable = () -> {
            Thread.sleep(2000);
            return "热水";
        };
        FutureTask futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        //刷牙
        Thread.sleep(1000);
        System.out.println("已经刷好牙");

        //取热水洗澡
        while(!futureTask.isDone()){
            System.out.println("准备热水中...");
            Thread.sleep(500);
        }
        System.out.println(futureTask.get()+"好了，可以洗澡了。");
        System.out.println("Done! spend time :"+(System.currentTimeMillis()-start)+"ms");
    }
}
