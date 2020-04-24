package com.xiayiye.honorfirst.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.xiayiye.honorfirst.R;

/**
 * @author xiayiye
 */
public class InvertedImageActivity extends Activity {
    private ImageView mRevertImageView;
    //原图
    private Bitmap mSourceBitmap;
    //倒立图
    private Bitmap mRevertBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inverted_image);
        mRevertImageView = (ImageView) findViewById(R.id.im_revert);
        mSourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        mRevertImageView.setImageDrawable(new BitmapDrawable(getResources(), revertBitmap()));
    }

    private Bitmap revertBitmap() {
        //1.倒立图
        Matrix matrix = new Matrix();
        //以X轴向下翻转
        matrix.preScale(1, -1);
        int width = mSourceBitmap.getWidth();
        int height = mSourceBitmap.getHeight();

        //生成倒立图，宽度和原图一致，高度为原图的一半
        mRevertBitmap = Bitmap.createBitmap(mSourceBitmap, 0, height / 2, width, height / 2, matrix, false);

        //2.要生成原图加上倒立图，先生成一个可变空的Bitmap, 高度为原图高度的1.5倍（包括原图和倒立图的高度）
        //间隙空白
        int gap = 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height + height / 2, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);
        //绘制原图
        canvas.drawBitmap(mSourceBitmap, 0, 0, paint);
        //绘制倒立图
        canvas.drawBitmap(mRevertBitmap, 0, height + gap, paint);

        //3.画笔使用LinearGradient 线性渐变渲染
        LinearGradient lg = new LinearGradient(0, height + gap, width, bitmap.getHeight(), 0x90ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        paint.setShader(lg);

        //4.指定画笔的Xfermode 即绘制的模式（不同的模式，绘制的区域不同）
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        //5.在倒立图区，绘制矩形渲染图层
        canvas.drawRect(0, height + gap, width, bitmap.getHeight(), paint);
        paint.setXfermode(null);
        return bitmap;
    }

    //缩放图片
    private Bitmap resizeImage(Bitmap bitmap, int width, int height) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        float scaleWidth = width / originWidth;
        float scaleHeight = height / originHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, originWidth, originHeight, matrix, true);
    }
}
