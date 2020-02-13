package com.example.mvptest;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvptest.bean.HomeBean;
import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xiayiye
 * 查看当前类的子父类关系快捷键：ctrl + h
 * 查看当前类的结构 alt + 7
 */
public class MainActivity extends Activity implements HomeContract.Views {

    private HomePresenter homePresenter;
    private EditText etInput;
    private static Gson gson;
    private int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
    private int maxiNumPoolSize = corePoolSize * 2;

    static {
        gson = new Gson();
    }

    private TextView tvShoeResult;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.et_input);
        tvShoeResult = findViewById(R.id.tv_shoe_result);
        homePresenter = new HomePresenter(this);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://wanandroid.com/wxarticle/list/408/1/json").build();
        Log.e("打印线程1", Thread.currentThread().getName());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxiNumPoolSize,
                3,
                TimeUnit.HOURS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(() -> okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("打印线程2", Thread.currentThread().getName() + "==" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("打印线程3", Thread.currentThread().getName());
                String data = response.body().string();
                handler.post(() -> Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show());
                Log.e("打印结果：", data);
            }
        }));
        //遍历树形结构view
        ViewGroup llRoot = findViewById(R.id.ll_root);
        forData(llRoot);
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

    /**
     * 遍历ViewGroup的方法
     *
     * @param llRoot 根VieGroup
     */
    private void forData(ViewGroup llRoot) {
        int childCount = llRoot.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (llRoot.getChildAt(i) instanceof ViewGroup) {
                Log.e("打印ViewGroup的id", llRoot.getChildAt(i).getId() + "=");
                forData((ViewGroup) llRoot.getChildAt(i));
            } else {
                Log.e("打印View的id", llRoot.getChildAt(i).getId() + "=");
            }
        }
    }

    public void showResult(View view) {
        String s = etInput.getText().toString();
        homePresenter.setShowData(s);
    }

    @Override
    public void showSuccessData(HomeBean data) {
        String receiverData = gson.toJson(data);
        tvShoeResult.setText(receiverData);
//        Toast.makeText(getApplicationContext(), receiverData, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailData(String data) {
        tvShoeResult.setText(data);
    }
}
