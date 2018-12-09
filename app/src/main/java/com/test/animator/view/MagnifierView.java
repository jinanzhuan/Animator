package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/9.
 */

public class MagnifierView extends View {

    private Bitmap mBitmap;
    private int mPreX = -1;
    private int mPreY = -1;
    private static final int RADIUS = 200;
    private static final int FACTOR = 5;
    private ShapeDrawable mDrawable;

    public MagnifierView(Context context) {
        this(context, null);
    }

    public MagnifierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagnifierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mPreX = (int) event.getX();
                mPreY = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE :
                mPreX = (int) event.getX();
                mPreY = (int) event.getY();

                break;
            case MotionEvent.ACTION_UP :
                mPreX = -1;
                mPreY = -1;
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
            mBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
            BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * FACTOR, mBitmap.getHeight() * FACTOR, false)
                                                , Shader.TileMode.CLAMP
                                                , Shader.TileMode.CLAMP);
            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setShader(shader);
        }
        canvas.drawBitmap(mBitmap, 0, 0, null);
        Matrix matrix = new Matrix();
        matrix.setTranslate( - mPreX * FACTOR + RADIUS, -mPreY * FACTOR + RADIUS);
        mDrawable.getPaint().getShader().setLocalMatrix(matrix);
        mDrawable.setBounds(mPreX - RADIUS, mPreY - RADIUS, mPreX + RADIUS, mPreY + RADIUS);

        if(mPreX != -1 && mPreY != -1) {
            mDrawable.draw(canvas);
        }

    }
}
