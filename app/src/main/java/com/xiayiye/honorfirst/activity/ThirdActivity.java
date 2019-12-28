package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 */
public class ThirdActivity extends Activity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("打印生命周期3", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("ThirdActivity");
    }

    public void third(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("first","third");
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("打印生命周期3", "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("打印生命周期3", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("打印生命周期3", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("打印生命周期3", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("打印生命周期3", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("打印生命周期3", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("打印生命周期3", "onStop");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("打印activity:","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("打印activity:","onTouchEvent");
        return super.onTouchEvent(event);
    }
}
