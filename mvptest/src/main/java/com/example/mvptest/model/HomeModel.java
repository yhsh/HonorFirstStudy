package com.example.mvptest.model;

import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;

/**
 * @author xiayiye
 */
public class HomeModel implements HomeContract.Model {
    private String str = "要显示的数据";

    @Override
    public void setData(HomePresenter homePresenter) {
        //获取到数据后传给P层
        homePresenter.getData(str);
    }

    @Override
    public void requestData(String param) {
        //模拟网络请求获取数据
        str += param;
    }
}
