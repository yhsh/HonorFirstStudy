package com.xiayiye.honorfirst.singleinstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xiayiye
 * 通过静态内部类单例模式创建单个线程池的方法
 */
public class YhshThreadPoolFactory {

    private ExecutorService mThreadService = null;

    private YhshThreadPoolFactory() {
        mThreadService = Executors.newSingleThreadExecutor();
    }

    private static class YhshThreadPoolFactoryHolder {
        private static final YhshThreadPoolFactory INSTANCE = new YhshThreadPoolFactory();
    }

    public static YhshThreadPoolFactory getInstance() {
        return YhshThreadPoolFactoryHolder.INSTANCE;
    }

    public <T> Future<T> submitRequest(Runnable runnable, T result) {
        return mThreadService.submit(runnable, result);
    }

    public void executeRequest(Runnable runnable) {
        mThreadService.execute(runnable);
    }

}

