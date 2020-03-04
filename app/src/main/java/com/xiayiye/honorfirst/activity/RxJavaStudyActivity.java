package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiayiye.honorfirst.ActivityManageAll;
import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.bean.UserBean;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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
public class RxJavaStudyActivity extends Activity implements AdapterView.OnItemClickListener {

    private TextView tvShowRxJavaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_study);
        tvShowRxJavaData = findViewById(R.id.tv_show_rxjava_data);
        ListView lvRxJavaStudy = findViewById(R.id.lv_rxjava_study);
        lvRxJavaStudy.setOnItemClickListener(this);

    }

    public void start() {
//        getObservable().subscribe(getObserver());
        getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tvShowRxJavaData.append(s);
//                tvShowRxJavaData.setText(s);
                Log.e("打印", "Consumer1");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("打印", "Consumer2");

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e("打印", "Action");
                Log.e("打印当前线程Action", Thread.currentThread().getName());
            }
        });
    }

    /**
     * 下面方法表示切换子线程和主线程,
     * subscribeOn表示为Observable里面的subscribe方法切换线程,Schedulers.io()：表示切换子线程
     * observeOn表示为Observer切换线程,AndroidSchedulers.mainThread()：为切换Android主线程独有的方法,此方法在RxAndroid包中,
     */
    public Observable getObservable() {
        @NonNull Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                // emitter.onError(new Throwable("错误提示"));
                emitter.onNext("特色");
                emitter.onComplete();
                Log.e("打印当前线程subscribe", Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        @NonNull Observable<String> observable2 = Observable.just("明天要下雨", "温度下降", "你想怎么办？").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        @NonNull Observable<String> observable3 = Observable.fromCallable(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "明天天气不好";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable1;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                start();
                break;
            case 1:
                rxJavaLogin();
                break;
            case 2:
                rxJavaSearch();
                break;
            case 3:
                rxJavaClickOne();
                break;
            default:
        }
    }

    private void rxJavaClickOne() {
        ActivityManageAll.getInstance1().goActivity(RxJavaClickOneActivity.class);
    }

    private void rxJavaSearch() {
        ActivityManageAll.getInstance1().goActivity(RxJavaSearchActivity.class);
    }

    private void rxJavaLogin() {
        Observable.just("张三").flatMap(new Function<String, ObservableSource<UserBean>>() {
            @Override
            public ObservableSource<UserBean> apply(String s) throws Exception {
                UserBean userBean = new UserBean();
                Log.e("打印参数", s + "");
                userBean.setName(s);
                return Observable.just(userBean);
            }
        }).subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) throws Exception {
                tvShowRxJavaData.setText(userBean.getName());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }
}
