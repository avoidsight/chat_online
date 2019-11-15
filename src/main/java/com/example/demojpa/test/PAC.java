package com.example.demojpa.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: zp
 * @Date: 2019-09-24 15:27
 * @Description:
 */
public class PAC {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i<1000; i++){
            new Thread(()->{
                try {
                    Thread.sleep(20);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        Thread.sleep(200);

        System.out.println("count = " + count);

    }
}
