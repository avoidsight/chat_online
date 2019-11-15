package com.example.demojpa.test;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: zp
 * @Date: 2019-09-24 15:27
 * @Description:
 */
public class Consumer {
    private Queue queue;

    private Lock lock;

    private Condition condition ;

    Consumer(Queue queue, Lock lock){
        this.queue = queue;
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public void consume() throws InterruptedException {
        lock.lock();
        while(queue.isEmpty()){
            System.out.println("暂时没有消息待处理...");
            condition.await();
        }
        Thread.sleep(1);
        System.out.println("一条消息已被处理");
        queue.poll();
        condition.signalAll();
        lock.unlock();
    }

}
