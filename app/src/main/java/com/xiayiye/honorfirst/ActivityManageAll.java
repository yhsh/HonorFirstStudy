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

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.xiayiye.honorfirst.utils.XiaYiYeUtils;

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/2/25 21:09
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.utils
 * 文件说明：Activit操作相关类
 */
public class ActivityManageAll {
    private ActivityManageAll() {
    }

    /**
     * 创建单例方法一
     */
    private static ActivityManageAll activityManageAll = new ActivityManageAll();

    public static ActivityManageAll getInstance1() {
        if (activityManageAll == null) {
            activityManageAll = new ActivityManageAll();
        }
        return activityManageAll;
    }

    /**
     * 创建单例方法二
     *
     * @return 返回单例对象
     */
    public static ActivityManageAll getInstance2() {
        return CreateActivityManageObject.ACTIVITY_MANAGE;
    }

    private static class CreateActivityManageObject {
        private static final ActivityManageAll ACTIVITY_MANAGE = new ActivityManageAll();
    }

    /**
     * 简单的跳转不带参数
     *
     * @param activity 要跳转的页面
     */
    public void goActivity(Class activity) {
        Application application = XiaYiYeUtils.getInstance().getApplicationByReflection();
        Intent intent = new Intent(application, activity);
        application.startActivity(intent);
    }

    /**
     * 简单的跳转带参数
     *
     * @param activity 要跳转的页面
     * @param bundle   携带的参数的封装体
     */
    public void goActivity(Class activity, Bundle bundle) {
        Application application = XiaYiYeUtils.getInstance().getApplicationByReflection();
        Intent intent = new Intent(application, activity);
        intent.putExtra(bundle.getClass().getSimpleName(), bundle);
        application.startActivity(intent);
    }
}
