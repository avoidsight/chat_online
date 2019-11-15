package com.example.demojpa.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: zp
 * @Date: 2019-09-27 10:12
 * @Description:
 */
public class CountLatchDownTest {

    private static CountDownLatch cdl = new CountDownLatch(1000);
    private static int sum = 0;
    private static int THREAD_SIZE = 1000;


    public static void main(String[] args) throws InterruptedException {

        for (int i=0;i<THREAD_SIZE;i++) {
            // 获得1000个线程的线程池
            ExecutorService executorService = getExecutors(THREAD_SIZE);
            executorService.execute(()->{
                // 计数器-1
                cdl.countDown();
                try {
                    // 线程等待
                    cdl.await();
                    // 触发某服务
                    Thread.sleep(10);
                    sum++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executorService.shutdown();
            cdl.countDown();
        }

        // 等待线程结果
        Thread.sleep(200);
        System.out.println("sum = " + sum);
        System.out.println("cdl.getCount() = " + cdl.getCount());
    }

    /**
     * 提供一个线程池
     * @param size 线程池的大小
     * @return
     */
    private static ExecutorService getExecutors(int size) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(size,size,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        return executorService;
    }
}
