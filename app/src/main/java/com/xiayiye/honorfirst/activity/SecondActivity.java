package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.custom.CustomNumView;

/**
 * @author xiayiye
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("打印生命周期2", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        CustomNumView customNumView = findViewById(R.id.cn_view);
        customNumView.setOnNumLetterListener(new CustomNumView.OnNumLetterListener() {
            @Override
            public void numLetter(String num) {
                Toast.makeText(SecondActivity.this, num, Toast.LENGTH_SHORT).show();
            }
        });
        setTitle("SecondActivity");
    }

    public void second(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("打印生命周期2", "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("打印生命周期2", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("打印生命周期2", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("打印生命周期2", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("打印生命周期2", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("打印生命周期2", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("打印生命周期2", "onStop");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
