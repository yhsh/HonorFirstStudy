package com.xiayiye.honorfirst.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
 * 创建时间：2020/5/19 18:15
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.custom
 * 文件说明：Android自定义view学习
 */
public class TestView extends View {

    private String data = "自定义View";
    private Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG);
    private Rect rect = new Rect();

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //设置画笔属性
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestView);
        boolean aBoolean = typedArray.getBoolean(R.styleable.TestView_test_boolean, false);
        int integer = typedArray.getInteger(R.styleable.TestView_test_integer, -1);
        float dimension = typedArray.getDimension(R.styleable.TestView_test_dimension, 0);
        int anInt = typedArray.getInt(R.styleable.TestView_test_enum, 1);
        data = typedArray.getString(R.styleable.TestView_test_string);
        Log.e("打印具体值：", data + " " + aBoolean + " " + anInt + " " + dimension + " " + integer);

        //获取属性的方法二,避免未设置属性报空
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.TestView_test_string:
                    data = typedArray.getString(R.styleable.TestView_test_string);
                    break;
                case R.styleable.TestView_test_boolean:
                    aBoolean = typedArray.getBoolean(R.styleable.TestView_test_boolean, false);
                    break;
                default:
                    break;
            }
        }
        //释放
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            //如果是具体值确定的大小就直接设置传入的值
            width = widthSize;
        } else {
            int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                //最大只能取传入的值的大小
                width = Math.min(widthSize, needWidth);
            } else {
                //不限定大小 MeasureSpec.UNSPECIFIED,测量多大就设置多大
                width = needWidth;
            }
        }
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            //具体值
            height = heightSize;
        } else {
            int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                //最大只能取传入的值
                height = Math.min(heightSize, needHeight);
            } else {
                //测量多大就有多大
                height = needHeight;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int measureHeight() {
        return 0;
    }

    private int measureWidth() {
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;
        //画圆:参数意思分别圆心坐标，半径
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - paint.getStrokeWidth() / 2, paint);
        //画线:参数意思分别是线的开始坐标和线的结束坐标
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        //横线
        canvas.drawLine(0, halfHeight, getWidth(), halfHeight, paint);
        //竖线
        canvas.drawLine(halfWidth, getHeight(), halfWidth, 0, paint);
        //画文字,将文字画在正中间
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.getTextBounds(data, 0, data.length(), rect);
        canvas.drawText(data, 0, halfHeight + rect.height() / 2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //改变绘制的文本
            data = "这是密码:8888";
            paint.setColor(Color.YELLOW);
            //重新绘制,只能再主线程刷新走onDraw方法
//            invalidate();
            //不走onDraw方法，走onLayout和onMeasure方法
//            requestLayout();
            //可在非UI线程刷新也就是子线程刷新UI
            postInvalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
