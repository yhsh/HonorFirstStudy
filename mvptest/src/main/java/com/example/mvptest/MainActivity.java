package com.example.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvptest.contract.HomeContract;
import com.example.mvptest.presenter.HomePresenter;

/**
 * @author xiayiye
 */
public class MainActivity extends Activity implements HomeContract.Views {

    private HomePresenter homePresenter;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.et_input);
        homePresenter = new HomePresenter(this);
    }

    public void showResult(View view) {
        String s = etInput.getText().toString();
        homePresenter.setShowData(s);
    }

    @Override
    public void showData(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }
}
