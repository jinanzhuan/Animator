package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/2.
 */

public class BitmapShadowView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mExtractAlpha;

    public BitmapShadowView(Context context) {
        super(context);
        init();
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wsj2015);
        mExtractAlpha = mBitmap.extractAlpha();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        int width = 200;
        int height = width * mExtractAlpha.getHeight() / mExtractAlpha.getWidth();
        canvas.save();
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        mPaint.setColor(Color.GRAY);
        canvas.drawBitmap(mExtractAlpha, null, new Rect(10, 10, width, height), mPaint);

        canvas.translate(-10, -10);
        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mBitmap, null, new Rect(10, 10, width, height), mPaint);
        canvas.restore();

    }
}
