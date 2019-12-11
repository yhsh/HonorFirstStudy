package com.xiayiye.honorfirst.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 * 说明：自定义view字母列表页面
 */
public class CustomNumView extends View {
    String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;
    private Rect bounds;
    private int geZiWidth;
    private float geZiHeight;
    private int selectNum = -1;
    int index;
    private int textColor;
    private boolean isLeft;
    private boolean isCenter;
    private boolean isRight;

    public CustomNumView(Context context) {
        this(context, null);
    }

    public CustomNumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNumView);
        textColor = typedArray.getColor(R.styleable.CustomNumView_num_text_color, Color.WHITE);
        float dimension = typedArray.getDimension(R.styleable.CustomNumView_num_text_size, 10);
        isLeft = typedArray.getBoolean(R.styleable.CustomNumView_num_text_left, false);
        isCenter = typedArray.getBoolean(R.styleable.CustomNumView_num_text_center, false);
        isRight = typedArray.getBoolean(R.styleable.CustomNumView_num_text_right, false);
        typedArray.recycle();
        paint = new Paint();
        paint.setColor(textColor);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
        paint.setTextSize(dimension);
        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            float textWidth = paint.measureText(str);
            float textHeight = bounds.height();
//            float x = geZiWidth * 0.5f - textWidth * 0.5f;
            float y = (geZiHeight * 0.5f + textHeight * 0.5f) + i * geZiHeight;
            float x;
            if (isCenter) {
                //xml中定义了居中就居中显示
                x = geZiWidth * 0.5f - textWidth * 0.5f;
            } else if (isLeft) {
                //xml中定义了左边就左边显示
                x = 0;
            } else if (isRight) {
                //xml中定义了右边就右边显示
                x = geZiWidth - textWidth;
            } else {
                //默认居中
                x = geZiWidth * 0.5f - textWidth * 0.5f;
            }
            paint.getTextBounds(str, 0, str.length(), bounds);
            paint.setColor(selectNum == i ? Color.RED : textColor);
            canvas.drawText(str, x, y, paint);

//            canvas.drawText(str, geZiWidth * 0.5f - paint.measureText(str) * 0.5f, getHeight()/26*(i+1), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        index = (int) (event.getY() / geZiHeight);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                showText();
                break;
            case MotionEvent.ACTION_UP:
                selectNum = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    private void showText() {
        if (index < data.length && selectNum != index) {
            selectNum = index;
            if (numLetterListener != null) {
                numLetterListener.numLetter(data[selectNum]);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        geZiWidth = getMeasuredWidth();
        geZiHeight = getMeasuredHeight() * 1.0f / data.length;
    }

    public interface OnNumLetterListener {
        void numLetter(String num);
    }

    OnNumLetterListener numLetterListener;

    public void setOnNumLetterListener(OnNumLetterListener numLetterListener) {
        this.numLetterListener = numLetterListener;
    }
}
