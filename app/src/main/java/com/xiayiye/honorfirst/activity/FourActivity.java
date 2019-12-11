package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 */
public class FourActivity extends Activity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("打印生命周期4", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("FourActivity");
    }

    public void toFirst(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("first","four");
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("打印生命周期4", "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("打印生命周期4", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("打印生命周期4", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("打印生命周期4", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("打印生命周期4", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("打印生命周期4", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("打印生命周期4", "onStop");
    }
}
