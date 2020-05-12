package com.xiayiye.honorfirst;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/5/11 20:08
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst
 * 文件说明：
 */
public class MianShi {
    public static void main(String[] args) {
//        String s = new String("1");
//        add(s);
//        System.out.println(s);


        A a = new A();
        a.addB(1);
        List<A> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.addC(i);
            list.add(a);
        }
        for (A a1 : list) {
            System.out.println(a1.getB() + "==" + a1.getC());
        }
    }

    private static void add(String s) {
        s += "1";
    }

    static class A {
        int b;
        int c;

        public void addB(int i) {
            b = i;
        }

        public int getB() {
            return b;
        }

        public void addC(int i) {
            c = i;
        }

        public int getC() {
            return c;
        }
    }
}
