package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xiayiye.honorfirst.R;

import androidx.annotation.Nullable;

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

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/3/9 13:32
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.activity
 * 文件说明：
 */
public class LineAnimateActivity extends Activity {

    private FrameLayout flContainer;
    private ImageButton ibAdd;
    private ImageView imgCart;
    private int[] outLocation = new int[2];
    private int[] carLocation = new int[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_animate);
        initView();
        initListener();
    }

    private void initListener() {
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示购物车抛物线动画
                showAnimate();
            }
        });
    }

    private void showAnimate() {
        //在同一个位置复制一个加号按钮
        ImageButton ib = new ImageButton(this);
        //设置复制图片的背景
        ib.setBackgroundResource(R.mipmap.button_add);
        //拿到原图片的坐标
        ibAdd.getLocationInWindow(outLocation);
        imgCart.getLocationInWindow(carLocation);
        //将原图在屏幕上面的坐标设置给复制的图片
        Log.e("打印坐标", "X:" + outLocation[0] + "==Y:" + (outLocation[1] - getStatusBarHeight()));
        ib.setX((float) outLocation[0]);
        //要减去状态栏的高度
        ib.setY((float) (outLocation[1] - getStatusBarHeight()));
        //添加到窗体上
        flContainer.addView(ib, ibAdd.getWidth(), ibAdd.getHeight());
        Log.e("打印宽高：", ibAdd.getX() + "=" + ibAdd.getY() + "-----" + ib.getX() + "=" + ib.getY());
        //开始抛物线动画组合
        AnimationSet animationSet = new AnimationSet(false);
        TranslateAnimation translateAnimationX = new TranslateAnimation(Animation.ABSOLUTE, 0F, Animation.ABSOLUTE, carLocation[0] - outLocation[0], Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f);
        TranslateAnimation translateAnimationY = new TranslateAnimation(Animation.ABSOLUTE, 0F, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, carLocation[1] - outLocation[1]);
        //Y轴增加加速效果
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        animationSet.addAnimation(translateAnimationX);
        animationSet.addAnimation(translateAnimationY);
        animationSet.setDuration(1500);
        //开始执行动画
        ib.startAnimation(animationSet);
        //设置动画结束的监听
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束，移除动画
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //方法一：
//                        ((ViewGroup) ib.getParent()).removeView(ib);
                        //方法二：
                        flContainer.removeView(ib);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView() {
        ibAdd = findViewById(R.id.ib_add);
        imgCart = findViewById(R.id.imgCart);
        flContainer = findViewById(R.id.fl_Container);
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
