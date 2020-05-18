package com.example.mvptest.utils;
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

import com.example.mvptest.api.RequestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/5/18 21:47
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.example.mvptest.utils
 * 文件说明：
 */
public class RetrofitSingle<T> {
    private RetrofitSingle() {
    }

    public static RetrofitSingle getInstance() {
        return NewInstance.RETROFIT_SINGLE;
    }

    private static class NewInstance {
        private static final RetrofitSingle RETROFIT_SINGLE = new RetrofitSingle();
    }

    public RequestApi getRetrofit() {
        return new Retrofit.Builder().baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RequestApi.class);
    }
}
