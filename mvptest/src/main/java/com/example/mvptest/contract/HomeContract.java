package com.example.mvptest.contract;

import com.example.mvptest.presenter.HomePresenter;

public interface HomeContract {
    interface Model {
        void setData(HomePresenter homePresenter);
    }

    interface Views {
        void showData(String data);

    }

    interface Presenter {
        void getData(String data);
    }
}
