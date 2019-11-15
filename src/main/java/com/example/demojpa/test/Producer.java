package com.example.demojpa.test;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: zp
 * @Date: 2019-09-25 14:26
 * @Description:
 */
public class Producer {
    /**
     * 同步队列中最大容量
     */
    private static int MAX_SIZE = 2;

    /**
     * 存放消息的队列
     */
    private Queue queue ;

    /**
     * 同步锁，相较于synchronized更轻量
     * lock基于java的AQS实现，属于代码实现
     * synchronized基于操作系统的互斥锁Mutex
     */
    private Lock lock;

    /**
     * 基于lock的唤醒机制
     */
    private Condition condition;

    Producer(Queue queue,Lock lock){
        this.queue = queue;
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public void produce() throws InterruptedException {

        lock.lock();
        while(queue.size()==MAX_SIZE){
            System.out.println("队列已满，请稍后...");
            condition.await();
        }

        // 增加元素，与add()不同的是，offer()当队列满了时，返回false，add()返回异常
        queue.offer(new Object());
        Thread.sleep(1);
        System.out.println("新增一条消息");

        // 唤醒等待中的线程，和Object的notifyAll()相似
        condition.signalAll();
        lock.unlock();
    }
}
