package com.example.mvptest.presenter;

import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.model.HomeModel;

/**
 * @author xiayiye
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.Views view;
    private static final HomeContract.Model MODEL = new HomeModel();

    public HomePresenter(HomeContract.Views view) {
        this.view = view;
    }

    @Override
    public void getData(String data) {
        view.showData(data);
    }

    /**
     * activity调用获取数据的方法
     */
    public void setShowData() {
        MODEL.setData(this);
    }
}
