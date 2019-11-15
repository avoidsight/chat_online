package com.example.demojpa.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zp
 * @Date: 2019-09-24 16:58
 * @Description:
 */
public class PACTest {
    public static void main(String[] args) {
        Queue queue = new LinkedList();
        Lock lock = new ReentrantLock();

        Producer producer = new Producer(queue,lock);
        Consumer consumer = new Consumer(queue,lock);

        for(int i = 0; i<10; i++){
            new Thread(()->{
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    consumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
