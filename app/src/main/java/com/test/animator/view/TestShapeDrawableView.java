package com.test.animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.animator.R;

/**
 * Created by shuwei on 2018/12/9.
 */

public class TestShapeDrawableView extends View {

    private ShapeDrawable mDrawable;
    private Paint mPaint;

    public TestShapeDrawableView(Context context) {
        this(context, null);
    }

    public TestShapeDrawableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestShapeDrawableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
        mDrawable = new ShapeDrawable(new RectShape());
        mDrawable.getPaint().setShader(
                new BitmapShader(Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()*3, bitmap.getHeight()*3, false)
                        , Shader.TileMode.REPEAT
                        , Shader.TileMode.REPEAT));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        matrix.setTranslate(-460*2, -365*2);
        mDrawable.getPaint().getShader().setLocalMatrix(matrix);
        mDrawable.setBounds(0, 0, getWidth(), getHeight());
        mDrawable.draw(canvas);
        canvas.drawPoint(460, 365, mPaint);
    }
}
