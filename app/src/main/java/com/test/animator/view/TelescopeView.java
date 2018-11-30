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
 * Created by ljn on 2018/11/29.
 */

public class TelescopeView extends View {
    private Bitmap mBitmap;
    private ShapeDrawable mDrawable;
    private static final int RADIUS = 200;
    private static final int FACTOR = 3;
    private final Matrix mMatrix = new Matrix();

    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //取消硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(mDrawable != null) {
            mMatrix.setTranslate(RADIUS - x*FACTOR, RADIUS - y*FACTOR);
            mDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
            mDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if(mBitmap == null) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
                mBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
                BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * FACTOR, mBitmap.getHeight() * FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                mDrawable = new ShapeDrawable(new OvalShape());
                mDrawable.getPaint().setShader(shader);
                mDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);
            }
            canvas.drawBitmap(mBitmap, 0, 0, null);
            mDrawable.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
