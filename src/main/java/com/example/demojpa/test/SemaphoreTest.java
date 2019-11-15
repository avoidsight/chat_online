package com.example.demojpa.test;

import java.util.concurrent.*;

/**
 * @author: zp
 * @Date: 2019-09-17 16:20
 * @Description:
 */
public class SemaphoreTest {
    /**
     * 每次只允许并发十个线程
     */
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ExecutorService executors = Executors.newCachedThreadPool();
        Future future = null;
        int threadNum = 100;

        for (int i = 0; i < threadNum; i++) {
            future = executors.submit(() -> {
                try {
                    // 获取一个信号量
                    semaphore.acquire();
                    service();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放一个信号量
                    semaphore.release();
                }

            });
        }
        //阻塞上面的线程
        future.get();
        System.out.println("spend time ：" + (System.currentTimeMillis() - start));
    }

    private static void service() throws InterruptedException {
        // 假装是一个业务
        Thread.sleep(10);
    }
}
