package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/2.
 */

public class BitmapShaderView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = getWidth() / 3;
        float top = getHeight() / 3;
        float right = getWidth() * 2 / 3;
        float bottom = getHeight() *2 / 3;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
