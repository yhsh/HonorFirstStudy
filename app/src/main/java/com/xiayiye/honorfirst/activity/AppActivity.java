package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.ActivityManage;

public class AppActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ListView lvApp = findViewById(R.id.lv_app);
        lvApp.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //跳转以前学习的页面
                goToBeforeStudy();
                break;
            case 1:
                //跳转RxJava学习页面
                toRxJavaStudy();
                break;
            default:
        }
    }

    private void toRxJavaStudy() {
        ActivityManage.getInstance2().goActivity(RxJavaStudyActivity.class);
    }

    private void goToBeforeStudy() {
        ActivityManage.getInstance1().goActivity(MainActivity.class);
    }
}
