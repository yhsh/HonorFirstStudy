package com.example.mvptest.model;

import android.os.Handler;
import android.util.Log;

import com.example.mvptest.api.RequestApi;
import com.example.mvptest.bean.HomeBean;
import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xiayiye
 */
public class HomeModel implements HomeContract.Model {
    private String str = "要显示的数据";
    private HomePresenter homePresenter;
    Handler handler = new Handler();

    @Override
    public void setData(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    /**
     * 获取到数据后传递给P层
     *
     * @param param 参数
     */
    @Override
    public void requestData(String param) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://wanandroid.com/").addConverterFactory(GsonConverterFactory.create()).build();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<HomeBean> homeBeanCall = requestApi.requestData(param);
        //Retrofit同步方法
        /*new Thread() {
            @Override
            public void run() {
                String name1 = Thread.currentThread().getName();
                Log.e("打印线程1", name1);
                try {
                    Response<HomeBean> execute = homeBeanCall.execute();
                    HomeBean body = execute.body();
                    String s = new Gson().toJson(body);
                    Log.e("打印：", s + "=");
                    if (body.getData().getDatas().size() > 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String name2 = Thread.currentThread().getName();
                                Log.e("打印线程2", name2);
                                homePresenter.getSuccessData(body);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homePresenter.getFailData("暂无数据");
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/

        //Retrofit异步方法
        homeBeanCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                HomeBean body = response.body();
                String s = new Gson().toJson(response.body());
                Log.e("打印：", s + "=");
                assert body != null;
                if (body.getData().getDatas().size() > 0) {
                    homePresenter.getSuccessData(body);
                } else {
                    homePresenter.getFailData("暂无数据");
                }
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable throwable) {
                throwable.printStackTrace();
                String message = throwable.getMessage();
                String localizedMessage = throwable.getLocalizedMessage();
                Log.e("打印异常：", message + "================\n" + localizedMessage);
                homePresenter.getFailData("调用失败");
            }
        });
    }
}
