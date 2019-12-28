package com.xiayiye.honorfirst.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @author xiayiye
 */
public class CustomView extends View {
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("打印CustomView", "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("打印CustomView", "onTouchEvent");
        Toast.makeText(getContext(), "点击了里面的CustomView", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
