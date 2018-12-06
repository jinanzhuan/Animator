package com.test.animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/12/6
 *     desc   : 实现文字的定点
 *     modify :
 * </pre>
 */

public class TextView extends View {
    private Paint mPaint;
    public TextView(Context context) {
        super(context);
        init();
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = 10;
        int top = 10;
        int right = 200;
        int bottom = 60;
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(left, top, right, bottom, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        mPaint.setTextAlign(Paint.Align.CENTER);
        String content = "我爱中国";
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int baseLineX = (right-left)/2 + left;
        int baseLineY = (bottom - top)/2 + top + (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;
        canvas.drawText(content, baseLineX, baseLineY, mPaint);
    }
}
