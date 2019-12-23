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
        YhshThreadPoolFactory.getInstance().executeRequest(new Runnable() {
            @Override
            public void run() {
                //写相应的网络请求逻辑即可
            }
        });
        System.out.println("打印结果：" + getResult(3, 5));
    }

    /**
     * 思路：利用递归实现
     * Java 版本：计算一个数的n次幂的方法
     *
     * @param number 要计算的数
     * @param count  要计算数的幂
     */
    private static int getResult(int number, int count) {
        if (count > 0) {
            return number * getResult(number, count - 1);
        }
        return 1;
    }
}
