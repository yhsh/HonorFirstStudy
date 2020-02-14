package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.utils.ThreadUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends Activity {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private ImageView ivStart;

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("打印生命周期1", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");
        String first = getIntent().getStringExtra("first");
        Log.e("打印获取数据", first + "=");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(128),
                sThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        ivStart = findViewById(R.id.iv_start);
        showValue();
    }

    private void showValue() {
        try {
            PackageManager pm = getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("XiaYiYe");
            Toast.makeText(this, channel, Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException ignored) {
        }
    }

    public void first(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void four(View view) {
        startActivity(new Intent(this, FourActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("打印生命周期1", "onNewIntent");
        setIntent(intent);

        String first1 = intent.getStringExtra("first");
        Log.e("打印获取数据1", first1 + "=");
        String first2 = getIntent().getStringExtra("first");
        Log.e("打印获取数据2", first2 + "=");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("打印生命周期1", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("打印生命周期1", "onDestroy");
    }

    @Override
    protected void onRestart() {
        MyRunnable runnable = new MyRunnable();
        ThreadUtils.startThread(runnable);
        ThreadUtils.cancelThread(runnable);

        super.onRestart();
        Log.e("打印生命周期1", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("打印生命周期1", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("打印生命周期1", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("打印生命周期1", "onStop");
    }

    public void toDialogActivity(View view) {
        startActivity(new Intent(this, DialogActivity.class));
        overridePendingTransition(0, 0);
    }

    public void click1(View view) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.2f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setRepeatCount(2);
        ivStart.startAnimation(animation);
    }

    public void click2(View view) {
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 180);
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, 5f, 5f);
//        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        ivStart.startAnimation(rotateAnimation);
    }

    public void click3(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 2.0f, 0.5f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        ivStart.startAnimation(scaleAnimation);
    }

    public void click4(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(2);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        ivStart.startAnimation(translateAnimation);
    }

    public void click5(View view) {
        myTask.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    /**
     * 滑动冲突解决
     *
     * @param view view
     */
    public void errorActivity(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

    public void openBrowser(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri contentUrl = Uri.parse("http://www.baidu.com");
        intent.setData(contentUrl);
        startActivity(intent);
    }

    class myTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected void onPreExecute() {
            //主线程
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //主线程
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //主线程
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //子线程
            return null;
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "创建的runnable", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
