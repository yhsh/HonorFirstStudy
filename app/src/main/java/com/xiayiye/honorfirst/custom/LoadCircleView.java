package com.xiayiye.honorfirst.custom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xiayiye.honorfirst.R;

/**
 * 创建时间：2017/5/12
 * 编写者：黄伟才
 * 功能描述：
 *
 * @author xiayiye
 */

public class LoadCircleView extends View {
    /**
     * 第一个动画的索引
     */
    private int changeIndex = 0;
    /**
     * 圆的颜色值
     */
    private int[] colors = new int[]{
            getResources().getColor(R.color.color_red),
            getResources().getColor(R.color.color_blue),
            getResources().getColor(R.color.color_black)};
    /**
     * 圆的半径 默认10f
     */
    private Float circleRadius = 10f;
    /**
     * 偏移量
     */
    private Float maxWidth = 50f;

    /**
     * 当前偏移的X坐标
     */
    private Float currentX = 0f;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 属性动画
     */
    private ValueAnimator valueAnimator;
    /**
     * 持续时间
     */
    private int duration = 800;

    public LoadCircleView(Context context) {
        this(context, null);
    }

    public LoadCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadCircleView);
            circleRadius = typedArray.getFloat(R.styleable.LoadCircleView_circle_radius, circleRadius);
            if (circleRadius > 10) {
                maxWidth = circleRadius * 4;
            }
            duration = typedArray.getInt(R.styleable.LoadCircleView_duration, duration);
            typedArray.recycle();//记得回收
        }
        startAnimator();
    }

    /**
     * 位移动画
     */
    private void startAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0f, maxWidth, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentX = (Float) animation.getAnimatedValue();
                invalidate();//执行刷新onDraw()
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                changePoint(changeIndex);
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    /**
     * 先执行动画的目标和中间停止的动画目标交换
     * （颜色切换）
     *
     * @param index 最先执行的动画的索引
     */
    private void changePoint(int index) {
        int temp = colors[2];
        colors[2] = colors[index];
        colors[index] = temp;
        changeIndex = (index == 0) ? 1 : 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        /*左边圆**/
        paint.setColor(colors[0]);
        canvas.drawCircle(centerX - currentX, centerY, circleRadius, paint);
        /*右边圆**/
        paint.setColor(colors[1]);
        canvas.drawCircle(centerX + currentX, centerY, circleRadius, paint);
        /*中间圆**/
        paint.setColor(colors[2]);
        canvas.drawCircle(centerX, centerY, circleRadius, paint);
    }

    /**
     * 销毁View的时候回调
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            Log.e("打印模式：", "EXACTLY");
        } else {
            if (mode == MeasureSpec.AT_MOST) {
                Log.e("打印模式：", "AT_MOST");
            } else if (mode == MeasureSpec.UNSPECIFIED) {
                Log.e("打印模式：", "UNSPECIFIED");
            }
        }
        Log.e("打印宽高：", size + "");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
