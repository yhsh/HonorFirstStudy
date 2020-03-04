package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.utils.XiaYiYeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

/**
 * @author xiayiye
 */
public class RxJavaClickOneActivity extends Activity {

    private TextView tvShowFastResult;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_click_one);
        tvShowFastResult = findViewById(R.id.tv_show_fast_result);
        Button btFastClick = findViewById(R.id.bt_fast_click);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINA);

        //使用RxJava方法
        RxView.clicks(btFastClick).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Observer<Unit>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Unit unit) {
                Log.e("打印操作快速点击", "onNext" + System.currentTimeMillis());
                tvShowFastResult.setText(simpleDateFormat.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                Log.e("打印操作快速点击", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("打印操作快速点击", "onComplete");
            }
        });
        //下面是合并的方法
        Observable.merge(new Observable<ObservableSource<String>>() {
            @Override
            protected void subscribeActual(Observer<? super ObservableSource<String>> observer) {

            }
        }, new Observable<Observable<String>>() {
            @Override
            protected void subscribeActual(Observer<? super Observable<String>> observer) {

            }
        }).subscribe(new Observer<ObservableSource<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ObservableSource<String> stringObservableSource) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /*public void fastClick(View view) {
        long end = 0;
        String time = simpleDateFormat.format(new Date());
//        DateUtils.
        //普通方法防止多次点击
        if (XiaYiYeUtils.getInstance().isFastClick()) {
            return;
        }
        tvShowFastResult.setText(simpleDateFormat.format(new Date()));

    }*/
}
