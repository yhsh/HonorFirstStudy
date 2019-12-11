package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xiayiye.honorfirst.R;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
