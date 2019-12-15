package com.example.mvptest.api;

import com.example.mvptest.bean.HomeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author xiayiye
 */
public interface RequestApi {
    /**
     * https://wanandroid.com/wxarticle/list/408/1/json
     *
     * @param param 参数
     * @return 返回数据
     */
    @GET("/wxarticle/list/{id}/1/json")
    Call<HomeBean> requestData(@Path("id") String param);
}
