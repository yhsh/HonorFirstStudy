package com.xiayiye.honorfirst.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
 * 创建时间：2020/4/29 21:24
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.custom
 * 文件说明：
 */
public class SurfaceViewBall extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    boolean isDraw = true;
    Paint paint;
    Ball cueBall;
    Ball[] balls;

    public SurfaceViewBall(Context context) {
        this(context, null);
    }

    public SurfaceViewBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDraw = false;
    }

    @Override
    public void run() {
        cueBall = new Ball(getMeasuredWidth() - 150, getMeasuredHeight() - 200, 0, 0, 40);
        //创建多个球
        balls = new Ball[]{
                new Ball(150, 200, 0, 0, 40),
                new Ball(getMeasuredWidth() - 150, 200, 0, 0, 40),
                new Ball(150, getMeasuredHeight() - 200, 0, 0, 40),
                new Ball(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 0, 0, 40)
        };
        while (isDraw) {
            drawMain();
        }
    }

    private void drawMain() {
        canvas = surfaceHolder.lockCanvas();
        //清空画布
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
        //先画一个圆
        drawBall();
        drawBalls();
        collStation();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void drawBalls() {
        for (Ball ball : balls) {
            paint.setColor(Color.RED);
            if (canvas != null) {
                canvas.drawCircle(ball.x, ball.y, ball.radius, paint);
            }
            ball.x = ball.x + ball.vX;
            ball.y = ball.y + ball.vY;
        }
    }

    /**
     * 判断两个球相撞
     *
     * @param ball1 球1
     * @param ball2 球2
     */
    private void collStationBalls(Ball ball1, Ball ball2) {
        //两个球中心点的距离小于球半径证明相撞了,两个球半径相同的情况下
        float lengthA = (ball2.x - ball1.x) * (ball2.x - ball1.x);
        float lengthB = (ball2.y - ball1.y) * (ball2.y - ball1.y);
        float lengthC = (float) Math.sqrt(lengthA + lengthB);
        if (lengthC / 2 <= ball1.radius) {
            //圆1 的方向
            ball1.vX = -(ball2.x - ball1.x) / 4;
            ball1.vY = -(ball2.y - ball1.y) / 4;
            //圆2 的方向同理
            ball2.vX = -(ball1.x - ball2.x) / 4;
            ball2.vY = -(ball1.y - ball2.y) / 4;
        }
    }

    private void drawBall() {
        //画母球
        paint.setColor(Color.BLUE);
        if (canvas != null) {
            canvas.drawCircle(cueBall.x, cueBall.y, cueBall.radius, paint);
        }
        //每一帧根据 vX vY 移动
        cueBall.x = cueBall.x + cueBall.vX;
        cueBall.y = cueBall.y + cueBall.vY;
        //验证母球和边框的算法
        collStationBall(cueBall);
    }

    /**
     * 验证相撞
     */
    public void collStation() {
        for (int i = 0; i < balls.length; i++) {
            //先验证边框碰撞
            collStationBall(balls[i]);
            //验证与母球相撞
            collStationBalls(cueBall, balls[i]);
            for (int j = 0; j < balls.length; j++) {
                //再验证两球碰撞
                if (i != j) {
                    //自己与自己不验证
                    collStationBalls(balls[i], balls[j]);
                }
            }
        }
    }

    /**
     * 验证碰撞边框
     *
     * @param ball 球
     */
    private void collStationBall(Ball ball) {
        //X方向碰撞
        if (ball.x <= ball.radius) {
            ball.vX = -ball.vX;
        }
        if (ball.x + ball.radius >= getMeasuredWidth()) {
            ball.vX = -ball.vX;
        }
        //Y方向碰撞同理
        if (ball.y <= ball.radius) {
            ball.vY = -ball.vY;
        }
        if (ball.y + ball.radius >= getMeasuredHeight()) {
            ball.vY = -ball.vY;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //让母球动起来
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //通过点击的点设置其速度无论在那个点点击都需要100帧到达
            cueBall.vX = (event.getX() - cueBall.x) / 100;
            cueBall.vY = (event.getY() - cueBall.y) / 100;
        }
        return super.onTouchEvent(event);
    }

    class Ball {
        //圆心坐标
        float x, y;
        //X,Y 轴移动的速度
        float vX, vY;
        //圆的半径
        float radius = 40f;

        public Ball(float x, float y, float vX, float vY, float radius) {
            this.x = x;
            this.y = y;
            this.vX = vX;
            this.vY = vY;
            this.radius = radius;
        }
    }
}
