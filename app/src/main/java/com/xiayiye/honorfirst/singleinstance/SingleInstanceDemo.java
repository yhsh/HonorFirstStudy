package com.xiayiye.honorfirst.singleinstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiayiye
 */
public class SingleInstanceDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Class<? extends List> aClass = list.getClass();
        try {
            Method add = aClass.getMethod("add", Object.class);
            try {
                add.invoke(list, "测试数据");
                System.out.println(list);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxiNumPoolSize = corePoolSize * 2 + 1;
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxiNumPoolSize,
                1,
                TimeUnit.HOURS,
                new LinkedBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            int taskId = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程的名称:" + Thread.currentThread().getName() + "---正在执行的task：" + taskId);
                    System.out.println("缓冲队列中等待的任务数量：" + threadPoolExecutor.getQueue().size()
                            + "-----------打印hash值:" + SingleInstanceObject.getInstance().hashCode()
                            + " 核心数：" + corePoolSize
                            + "--当前线程池个数：" + threadPoolExecutor.getPoolSize()
                            + "--主动执行任务的近似线程数：" + threadPoolExecutor.getActiveCount());
                }
            });
        }
        // 按过去执行已提交任务的顺序发起一个有序的关闭，但是不接受新任务。
        threadPoolExecutor.shutdown();
        YhshThreadPoolFactory.getInstance().executeRequest(new Runnable() {
            @Override
            public void run() {
                //写相应的网络请求逻辑即可
            }
        });
        System.out.println("打印结果：" + getResult(3, 5));
        getMap();
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

    /**
     * 遍历 Map 集合
     */
    private static void getMap() {
        Map<String, String> data = new HashMap<>();
        data.put("1", "葫芦娃");
        data.put("2", "葫芦爷爷");
        data.put("3", "蛇精");
        Set<String> keys = data.keySet();
        for (String key : keys) {
            System.out.println("打印Map的建：" + key);
            System.out.println("打印Map的值1：" + data.get(key));
        }
        Collection<String> values = data.values();
        for (String value : values) {
            System.out.println("打印Map的值2：" + value);
        }

        //遍历方式二
        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("打印Map的entrySet键：" + entry.getKey() + "--值：" + entry.getValue());
        }
    }
}
