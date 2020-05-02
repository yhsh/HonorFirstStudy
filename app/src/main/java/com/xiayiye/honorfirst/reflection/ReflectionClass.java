package com.xiayiye.honorfirst.reflection;
/*
 * Copyright (c) 2020, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/5/2 14:51
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.utils
 * 文件说明：反射的相关练习
 */
public class ReflectionClass {
    public static void main(String[] args) {
        try {
            Class threadUtils = Class.forName("com.xiayiye.honorfirst.reflection.Person");
            //创建对象
            Object newInstance = threadUtils.newInstance();
            //获取所有public构造方法
            Constructor[] constructor = threadUtils.getConstructors();
            //获取所有构造方法包括私有
            Constructor[] constructorDeclared = threadUtils.getDeclaredConstructors();
            Constructor declaredConstructor = threadUtils.getDeclaredConstructor(String.class, int.class, String.class, String.class);
            declaredConstructor.setAccessible(true);
            Object instancePerson = declaredConstructor.newInstance("张三", 28, "男", "Android开发工程师");
            System.out.println(instancePerson);
            System.out.println("------------------下面是使用暴力反射修改后的值-----------------------");
            //对成员变量使用暴力发射,修改成员变量
            Field name = threadUtils.getDeclaredField("name");
            Field age = threadUtils.getDeclaredField("age");
            Field sex = threadUtils.getDeclaredField("sex");
            Field work = threadUtils.getDeclaredField("work");
            name.setAccessible(true);
            age.setAccessible(true);
            sex.setAccessible(true);
            work.setAccessible(true);
            name.set(instancePerson, "林青霞");
            age.set(instancePerson, 36);
            sex.set(instancePerson, "女");
            work.set(instancePerson, "Android开发高级工程师");
            System.out.println(instancePerson);

            System.out.println("------------------获取成员方法并调用此方法无参无返回值构造-----------------------");
            Method getWork = threadUtils.getDeclaredMethod("getWork", null);
            getWork.invoke(instancePerson, null);

            System.out.println("------------------获取成员方法并调用此方法带参无返回值构造-----------------------");
            Method selfName = threadUtils.getDeclaredMethod("getSelfName", String.class);
            //使用暴力反射
            selfName.setAccessible(true);
            selfName.invoke(instancePerson, "扬宏豕慧");

            System.out.println("------------------获取成员方法并调用此方法带参带返回值构造-----------------------");
            Method getArgs = threadUtils.getDeclaredMethod("getArgs", String.class);
            //使用暴力反射强制访问此方法
            getArgs.setAccessible(true);
            Object returnData = getArgs.invoke(instancePerson, "下一页5");
            System.out.println("打印返回值的数据：" + returnData);


            //获取所有public成员变量的方法
            Field[] fields = threadUtils.getFields();
            //获取所有成员变量包括私有的方法
            Field[] fieldsDeclared = threadUtils.getDeclaredFields();
            //获取所有public成员方法
            Method[] methods = threadUtils.getMethods();
            //获取所有成员方法包括私有方法
            Method[] methodsDeclared = threadUtils.getDeclaredMethods();

            System.out.println("------------------获取所有public构造方法-----------------------");
            for (Constructor constructor1 : constructor) {
                System.out.println(constructor1);
            }
            System.out.println("------------------获取所有构造方法包括私有-----------------------");
            for (Constructor constructor2 : constructorDeclared) {
                System.out.println(constructor2);
            }
            System.out.println("-------------------获取所有public成员变量----------------------");
            for (Field field : fields) {
                System.out.println(field);
            }
            System.out.println("--------------------获取所有成员变量包括私有的---------------------");
            for (Field field : fieldsDeclared) {
                System.out.println(field);
            }
            System.out.println("-------------------获取所有public成员方法----------------------");
            for (Method method : methods) {
                System.out.println(method);
            }
            System.out.println("------------------获取所有成员方法包括私有方法-----------------------");
            for (Method method : methodsDeclared) {
                System.out.println(method);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
