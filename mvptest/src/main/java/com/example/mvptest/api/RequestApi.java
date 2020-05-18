package com.example.mvptest.api;

import com.example.mvptest.bean.HomeBean;
import com.example.mvptest.bean.LoginBean;
import com.example.mvptest.bean.RegisterBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 表单提交方法一，参数较多的时候可以使用FieldMap
     *
     * @param data 参数
     * @return 返回值
     */
    @FormUrlEncoded
    @POST("/user/register")
    Call<RegisterBean> register(@FieldMap Map<String, String> data);

    /**
     * 表单提交方式二，参数较少的时候使用Field
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 确认密码
     * @return 返回值
     */
    @FormUrlEncoded
    @POST("/user/register")
    Call<RegisterBean> registerField(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginBean> login(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginBean> loginField(@Field("username") String username, @Field("password") String password);
}
