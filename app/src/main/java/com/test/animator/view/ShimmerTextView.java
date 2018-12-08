package com.test.animator.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.*;
/**
 * Created by shuwei on 2018/12/8.
 */

public class ShimmerTextView extends android.widget.TextView {
    private Paint mPaint;
    private float mDx;
    private float mLength;

    public ShimmerTextView(Context context) {
        this(context, null);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = getPaint();
        mLength = mPaint.measureText(getText().toString().trim());
        createAnim(mLength);
        createLinearGradient(mLength);
    }

    private void createLinearGradient(float length) {
        LinearGradient gradient = new LinearGradient(-length + mDx, 0, 0 + mDx, 0
                , new int[]{getCurrentTextColor(), Color.GREEN, getCurrentTextColor()}
                , new float[]{0f, 0.5f, 1f}
                , Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
    }

    private void createAnim(float length) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, length*2);
        animator.setDuration(4000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        createLinearGradient(mLength);
        super.onDraw(canvas);
    }
}
