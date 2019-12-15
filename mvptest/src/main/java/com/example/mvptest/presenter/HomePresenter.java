package com.example.mvptest.presenter;

import com.example.mvptest.bean.HomeBean;
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
    public void getSuccessData(HomeBean data) {
        //在V层显示数据的方法
        view.showSuccessData(data);
    }

    @Override
    public void getFailData(String data) {
        view.showFailData(data);
    }

    /**
     * activity调用获取数据的方法
     *
     * @param param activity层网络请求的参数
     */
    public void setShowData(String param) {
        //先网络请求获取数据，然后回传给P层
        MODEL.requestData(param);
        MODEL.setData(this);
    }
}
