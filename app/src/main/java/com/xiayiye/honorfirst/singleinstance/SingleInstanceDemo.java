package com.xiayiye.honorfirst.singleinstance;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiayiye
 */
public class SingleInstanceDemo {
    public static void main(String[] args) {
        final int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxiNumPoolSize = corePoolSize * 2 + 1;
        for (int i = 0; i < 10; i++) {
            final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maxiNumPoolSize,
                    1,
                    TimeUnit.HOURS,
                    new LinkedBlockingQueue<Runnable>(),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("打印hash值:" + SingleInstanceObject.getInstance().hashCode() + " 核心数：" + corePoolSize + "对象：" + threadPoolExecutor.hashCode());
                }
            });
        }
    }
}
