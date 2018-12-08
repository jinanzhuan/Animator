package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/8.
 */

public class EraserView extends View {

    private Paint mPaint;
    private Bitmap mBmpSrc;
    private Bitmap mBmpDst;
    private Path mPath;
    private float mPreX, mPreY;
    private Bitmap mBmpText;

    public EraserView(Context context) {
        this(context, null);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(45);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mBmpText = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mPreX = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPreX, mPreY);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE :
                float endX = (mPreX + event.getX())/2;
                float endY = (mPreY + event.getY())/2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_UP :

                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBmpSrc == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
            mBmpSrc = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
            mBmpDst = Bitmap.createBitmap(mBmpSrc.getWidth(), mBmpSrc.getHeight(), Bitmap.Config.ARGB_8888);
        }
        canvas.drawBitmap(mBmpText, null, new Rect(0, 0, mBmpSrc.getWidth(), mBmpSrc.getHeight()), mPaint);

        int saveLayer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        Canvas c = new Canvas(mBmpDst);
        c.drawPath(mPath, mPaint);
        canvas.drawBitmap(mBmpDst, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mBmpSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(saveLayer);
    }
}
