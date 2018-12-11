package com.test.animator.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/12/7
 *     desc   :
 *     modify :
 * </pre>
 */

public class WaveView extends View {
    private Paint mPaint;
    private int mItemWaveLength = 400;
    private int mOriginY = 200;
    private int mWaveControlPonintHeight = 100;
    private ValueAnimator mValueAnimator;
    private long mAnimatorDuration = 2000;
    private boolean isInfinite = true;//是否重复
    private int mRepeatTimes = 1;
    private int mDx;//在x轴上的变化值
    private Path mWavePath;
    private boolean isClosedTop;//是否封闭顶部
    private boolean isStartAnimationNow = true;//是否立即开始动画

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mWavePath = new Path();
        initAnimation();
    }

    private void initAnimation() {
        mValueAnimator = ValueAnimator.ofInt(0, mItemWaveLength);
        mValueAnimator.setDuration(mAnimatorDuration);
        mValueAnimator.setRepeatCount(isInfinite?ValueAnimator.INFINITE:mRepeatTimes);
        mValueAnimator.setInterpolator(new LinearInterpolator());//线性插值器
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        if(isStartAnimationNow) {
            mValueAnimator.start();
        }
    }

    public void startAnimation() {
        if(!mValueAnimator.isRunning()) {
            mValueAnimator.start();
            postInvalidate();
        }
    }

    public void finishAnimation() {
        if(mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath.reset();
        int halfWaveLength = mItemWaveLength / 2;
        mWavePath.moveTo(-mItemWaveLength + mDx, mOriginY);
        for(int i = -mItemWaveLength; i < getWidth() + mItemWaveLength; i+= mItemWaveLength) {
            mWavePath.rQuadTo(halfWaveLength/2, - mWaveControlPonintHeight, halfWaveLength, 0);
            mWavePath.rQuadTo(halfWaveLength/2, mWaveControlPonintHeight, halfWaveLength, 0);
        }
        //最后封闭曲线
        if(isClosedTop) {
            mWavePath.lineTo(getWidth(), 0);
            mWavePath.lineTo(0, 0);
            mWavePath.close();
        }else {
            mWavePath.lineTo(getWidth(), getHeight());
            mWavePath.lineTo(0, getHeight());
            mWavePath.close();
        }
        canvas.drawPath(mWavePath, mPaint);
    }
}
