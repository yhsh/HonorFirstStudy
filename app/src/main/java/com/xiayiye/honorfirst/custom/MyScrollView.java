package com.xiayiye.honorfirst.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

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

/**********************************************************************
 *
 *
 * @类名 MyScrollView
 * @包名 com.example.apple.studydispatchtouchevent
 * @author 谢晗超
 * @创建日期 2018/4/23
 ***********************************************************************/
@TargetApi(Build.VERSION_CODES.M)
public class MyScrollView extends ScrollView {

    private MyListView mListView;
    private boolean mIsScrolledToTop = true;
    private boolean mIsScrolledToBottom = false;
    private ISmartScrollChangedListener mSmartScrollChangedListener;

    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //设置SctollView滑动监听
        mSmartScrollChangedListener = new ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
            }

            @Override
            public void onScrolledToTop() {
            }
        };
    }

    public void setIsBottom(boolean bottom) {
        mIsScrolledToBottom = bottom;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mIsScrolledToBottom == false) {
            //如果ScrollView没有滑动到底部，这时候事件还是应该交给
            //ScrollView去处理
            return super.onInterceptTouchEvent(ev);
        } else if (mIsScrolledToBottom == true && mListView.getHandleOver()) {
            //如果ScrollView滑动到了底部，但是我手指的滑动却没有放在
            //ListView上面，还是在ScrollView上进行滑动，这时
            //事件也应该交给ScrollView去处理
            return super.onInterceptTouchEvent(ev);
        } else {
            //剩下的情况交给子类去处理
            return false;
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            mIsScrolledToTop = clampedY;
            mIsScrolledToBottom = false;
        } else {
            mIsScrolledToTop = false;
            mIsScrolledToBottom = clampedY;
        }
        notifyScrollChangedListeners();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // API 9及之后走onOverScrolled方法监听
        if (android.os.Build.VERSION.SDK_INT < 9) {
            if (getScrollY() == 0) {
                mIsScrolledToTop = true;
                mIsScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
                mIsScrolledToBottom = true;
                mIsScrolledToTop = false;
            } else {
                mIsScrolledToTop = false;
                mIsScrolledToBottom = false;
            }
            notifyScrollChangedListeners();
        }
    }

    private void notifyScrollChangedListeners() {
        if (mIsScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (mIsScrolledToBottom) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToBottom();
            }
        }
    }


    public void setListView(MyListView listView) {
        this.mListView = listView;
    }

    public interface ISmartScrollChangedListener {
        void onScrolledToBottom();

        void onScrolledToTop();
    }
}

