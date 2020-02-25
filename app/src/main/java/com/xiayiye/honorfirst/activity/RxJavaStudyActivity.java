package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiayiye.honorfirst.R;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
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
 * 创建时间：2020/2/25 21:45
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.activity
 * 文件说明：RxJava学习的页面
 */
public class RxJavaStudyActivity extends Activity {

    private TextView tvShowRxJavaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_study);
        tvShowRxJavaData = findViewById(R.id.tv_show_rxjava_data);
    }

    public void start(View view) {
//        getObservable().subscribe(getObserver());
        getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                tvShowRxJavaData.append(s);
//                tvShowRxJavaData.setText(s);
                Log.e("打印", "Consumer1");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.e("打印", "Consumer2");

            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                Log.e("打印", "Action");

            }
        });
    }

    public Observable getObservable() {
        @NonNull Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                //emitter.onError(new Throwable("错误提示"));
                emitter.onNext("特色");
                emitter.onComplete();
            }
        });
        @NonNull Observable<String> observable2 = Observable.just("明天要下雨", "温度下降", "你想怎么办？");
        @NonNull Observable<String> observable3 = Observable.fromCallable(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "明天天气不好";
            }
        });
        return observable2;
    }

    public Observer getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("打印", "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("打印", "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("打印", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("打印", "onComplete");
            }
        };
    }
}
