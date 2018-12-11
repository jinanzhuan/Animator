package com.test.animator.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.test.animator.R;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/12/11
 *     desc   :
 *     modify :
 * </pre>
 */

public class LoadingImageView extends AppCompatImageView {
    private int mTop;
    private int count;
    private static final int IMAGE_COUNT = 3;
    public LoadingImageView(Context context) {
        super(context);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100, 0);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (int) animation.getAnimatedValue();
                setTop(mTop - dx);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setImageResource(R.drawable.dog_1);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                count ++;
                int position = count % IMAGE_COUNT;
                switch (position) {
                    case 0 :
                        setImageResource(R.drawable.dog_1);
                        break;
                    case 1 :
                        setImageResource(R.drawable.dog_2);
                        break;
                    case 2 :
                        setImageResource(R.drawable.dog_3);
                        break;
                }
            }
        });
        animator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }
}
