package com.xiayiye.honorfirst.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/*
 * Copyright (c) 2020, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/4/23 23:05
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：HonorFirst
 * 文件包名：com.xiayiye.honorfirst.custom
 * 文件说明：自定义显示倒影图片控件
 */
@SuppressLint("AppCompatCustomView")
public class InvertedImage extends ImageView {


    private Bitmap drawBitmap;

    public InvertedImage(Context context) {
        this(context, null);
    }

    public InvertedImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InvertedImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        //先获取原图
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Log.e("打印", bitmap.getHeight() + "==" + bitmap.getWidth());
        Matrix matrix = new Matrix();
        //缩放-1，可实现倒影不同于旋转
        matrix.preScale(1, -1);
        Bitmap invertedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        //绘制倒影的原图
        drawBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() + invertedBitmap.getHeight() / 2, Bitmap.Config.ARGB_8888);
        //先绘制原图，最终绘制在图片上面
        Canvas canvas = new Canvas(drawBitmap);
        //绘制原图
        canvas.drawBitmap(bitmap, new Matrix(), null);
        //在绘制倒影图片
        Matrix matrix1 = new Matrix();
        //从原图的高度处开始绘制
        matrix1.setTranslate(0, bitmap.getHeight());
        canvas.drawBitmap(invertedBitmap, matrix1, null);
        Paint paint = new Paint();
        //为倒影图片增加渐变效果,线渐变效果
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight(), 0x90ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, bitmap.getHeight(), getWidth(), getHeight(), paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制最终图片
        canvas.drawBitmap(drawBitmap, 0, 0, null);
    }
}