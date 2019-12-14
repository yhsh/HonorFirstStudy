package com.xiayiye.honorfirst.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.custom.MenuViewItem;

/**
 * @author xiayiye
 */
public class FourActivity extends Activity implements View.OnClickListener {

    private Intent intent;
    private MenuViewItem homeBtCenter;
    private MenuViewItem homeBtYq;
    private MenuViewItem homeBtFwsy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("打印生命周期4", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        setTitle("FourActivity");

        homeBtYq = findViewById(R.id.inquireabout);
        homeBtCenter = findViewById(R.id.Analysis);
        homeBtFwsy = findViewById(R.id.personal_center);
        homeBtCenter.setOnClickListener(this);
        homeBtYq.setOnClickListener(this);
        homeBtFwsy.setOnClickListener(this);
    }

    public void toFirst(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("first", "four");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inquireabout:
                startAnimator(homeBtYq);
                break;
            case R.id.Analysis:
                startAnimator(homeBtCenter);
                break;
            case R.id.personal_center:
//                startAnimator(homeBtFwsy);
                break;
            default:
                break;
        }
    }

    public void startAnimator(MenuViewItem menuViewItem) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(menuViewItem, "translationY", 0, 200, -200, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }
}
