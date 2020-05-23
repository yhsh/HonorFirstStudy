package com.xiayiye.honorfirst.uploadfile;
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

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/5/23 15:29
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.uploadfile
 * 文件说明：服务端代码
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //创建服务器socket对象
        ServerSocket ss = new ServerSocket(888);
        //监听客户端对象
        Socket s = ss.accept();
        //获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        //封装输出流
        PrintWriter pr = new PrintWriter(new FileWriter("copy.gradle"), true);
        String line = null;
        while ((line = br.readLine()) != null) {
            pr.println(line);
        }
        //给出反馈，获取输出流
        PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
        printWriter.println("文件上传成功！");
        //关流
        printWriter.close();
        ss.close();
        s.close();
    }
}
