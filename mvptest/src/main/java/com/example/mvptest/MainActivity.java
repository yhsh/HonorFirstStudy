package com.example.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;

/**
 * @author xiayiye
 */
public class MainActivity extends Activity implements HomeContract.Views {

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homePresenter = new HomePresenter(this);
    }

    public void showResult(View view) {
        homePresenter.setShowData();
    }

    @Override
    public void showData(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }
}
