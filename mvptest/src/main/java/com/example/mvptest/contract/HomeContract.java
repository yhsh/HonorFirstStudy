package com.example.mvptest.contract;

import com.example.mvptest.presenter.HomePresenter;

/**
 * @author xiayiye
 */
public interface HomeContract {
    interface Model {
        /**
         * 通过网络请求等获取到的数据bean,然后将数据通过P层设置给V层
         *
         * @param homePresenter 传递数据的中介 P 对象
         */
        void setData(HomePresenter homePresenter);

        /**
         * 网络请求的方法
         *
         * @param param 参数
         */
        void requestData(String param);
    }

    interface Views {
        /**
         * 在P层通过从M层拿到的数据回传给activity的方法
         *
         * @param data 拿到的数据
         */
        void showData(String data);

    }

    /**
     * P层从M拿到数据后传递给V层
     */
    interface Presenter {
        /**
         * 通过P层从M层拿到的数据回传给V层(也就是activity)
         *
         * @param data 拿到的数据
         */
        void getData(String data);
    }
}
