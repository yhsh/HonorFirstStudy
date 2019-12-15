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
        homePresenter.getData(str);
    }
}
