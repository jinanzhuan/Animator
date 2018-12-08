package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/7.
 */

public class TelescopeView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private float mRadiusX = -1;
    private float mRadiusY = -1;
    private float mRadius;
    private Bitmap mBitmapBg;

    public TelescopeView(Context context) {
        super(context);
        init();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mRadius = 150;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mRadiusX = event.getX();
                mRadiusY = event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE :
                mRadiusX = event.getX();
                mRadiusY = event.getY();
                break;
            case MotionEvent.ACTION_UP :
                mRadiusX = -1;
                mRadiusY = -1;
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmapBg == null) {
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();
            int heightBg = getWidth() * height / width;
            mBitmapBg = Bitmap.createBitmap(getWidth(),heightBg, Bitmap.Config.ARGB_8888);
            Canvas canvasBg = new Canvas(mBitmapBg);
            canvasBg.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), heightBg), mPaint);
        }

        if(mRadiusX != -1 && mRadiusY != -1) {
            BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            mPaint.setShader(bitmapShader);
            canvas.drawCircle(mRadiusX, mRadiusY, mRadius, mPaint);
        }
    }
}
