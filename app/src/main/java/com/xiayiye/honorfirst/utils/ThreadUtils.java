package com.xiayiye.honorfirst.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiayiye
 * 线程池工具
 */
public class ThreadUtils {
    /**
     * 根据cup核心数设置线程池数量
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    /**
     * 最大线程池数量= cpu核心数*2+1
     */
    private static int maximumPoolSize = corePoolSize * 2 + 1;
    /**
     * 等待线程的存活时间
     */
    private static long keepAliveTime = 1;
    /**
     * 等待线程存活时间的单位
     */
    private static TimeUnit unit = TimeUnit.HOURS;
    /**
     *
     */
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    /**
     * 默认的线程工厂
     */
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    /**
     * 取消策略，当超过等待线程池的数量后禁止添加了
     */
    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
    private static ThreadPoolExecutor executor;

    /**
     * 开启线程池
     *
     * @param runnable runnable
     */
    public static void startThread(Runnable runnable) {
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime, unit,
                workQueue,
                threadFactory,
                handler);
        executor.execute(runnable);
    }

    /**
     * 取消线程池
     *
     * @param runnable runnable
     */
    public static void cancelThread(Runnable runnable) {
        if (null == runnable || executor == null) {
            return;
        }
        executor.remove(runnable);
    }
}
