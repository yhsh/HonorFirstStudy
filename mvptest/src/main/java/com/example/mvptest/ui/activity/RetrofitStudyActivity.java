package com.example.mvptest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvptest.R;
import com.example.mvptest.api.RequestApi;
import com.example.mvptest.bean.LoginBean;
import com.example.mvptest.bean.RegisterBean;
import com.example.mvptest.utils.RetrofitSingle;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitStudyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_study);
        ListView lvRetrofit = findViewById(R.id.lv_retrofit);
        lvRetrofit.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startRegister();
                    break;
                case 1:
                    startLogin();
                    break;
                default:
                    break;
            }
        });
    }

    private void startLogin() {
        RequestApi requestApi = RetrofitSingle.getInstance().getRetrofit();
//        Map<String, String> data = new HashMap<>();
//        data.put("username", "zhangsheng1");
//        data.put("password", "123456");
//        Call<LoginBean> login = requestApi.login(data);

        //表单提交方法二(参数较少的时候使用)
        Call<LoginBean> login = requestApi.loginField("zhangsheng1", "123456");
        login.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean body = response.body();
                Log.e("打印数据", new Gson().toJson(body));
                Toast.makeText(RetrofitStudyActivity.this, new Gson().toJson(body), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Toast.makeText(RetrofitStudyActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startRegister() {
        RequestApi requestApi = RetrofitSingle.getInstance().getRetrofit();
//        Map<String, String> data = new HashMap<>();
//        data.put("username", "zhangsheng1");
//        data.put("password", "123456");
//        data.put("repassword", "123456");
//        Call<RegisterBean> register = requestApi.register(data);

        // 表单提交方式二
        Call<RegisterBean> register = requestApi.registerField("zhangsheng1", "123456", "123456");
        register.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                RegisterBean body = response.body();
                Log.e("打印数据", new Gson().toJson(body));
                Toast.makeText(RetrofitStudyActivity.this, new Gson().toJson(body), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                Toast.makeText(RetrofitStudyActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
