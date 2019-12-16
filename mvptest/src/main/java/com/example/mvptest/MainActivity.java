package com.example.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvptest.bean.HomeBean;
import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;
import com.google.gson.Gson;

/**
 * @author xiayiye
 * 查看当前类的子父类关系快捷键：ctrl + h
 * 查看当前类的结构 alt + 7
 */
public class MainActivity extends Activity implements HomeContract.Views {

    private HomePresenter homePresenter;
    private EditText etInput;
    private static Gson gson;

    static {
        gson = new Gson();
    }

    private TextView tvShoeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.et_input);
        tvShoeResult = findViewById(R.id.tv_shoe_result);
        homePresenter = new HomePresenter(this);
    }

    public void showResult(View view) {
        String s = etInput.getText().toString();
        homePresenter.setShowData(s);
    }

    @Override
    public void showSuccessData(HomeBean data) {
        String receiverData = gson.toJson(data);
        tvShoeResult.setText(receiverData);
//        Toast.makeText(getApplicationContext(), receiverData, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailData(String data) {
        tvShoeResult.setText(data);
    }
}
