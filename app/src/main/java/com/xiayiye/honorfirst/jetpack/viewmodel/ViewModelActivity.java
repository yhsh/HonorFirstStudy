package com.xiayiye.honorfirst.jetpack.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 */
public class ViewModelActivity extends AppCompatActivity {
    ViewModelDemo viewModelDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        viewModelDemo = ViewModelProviders.of(this).get(ViewModelDemo.class);
        TextView tvResult = findViewById(R.id.tv_result);
        //恢复数据
        tvResult.setText(String.valueOf(viewModelDemo.number));
        Button btAddOne = findViewById(R.id.bt_add_one);
        btAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelDemo.addValue(1);
                tvResult.setText(String.valueOf(viewModelDemo.number));
            }
        });
    }
}
