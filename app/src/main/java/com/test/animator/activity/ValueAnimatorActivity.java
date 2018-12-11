package com.test.animator.activity;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;

import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/11/29
 *     desc   :
 *     modify :
 * </pre>
 */

public class ValueAnimatorActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.tv_char)
    TextView mTvChar;
    @InjectView(R.id.btn_start)
    Button mBtnStart;

    private boolean flag;
    private ValueAnimator mAnimator;
    private ValueAnimator mCharAnimator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_value_animator;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ValueAnimatorActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        initAnimator();
    }

    private void initAnimator() {
        mAnimator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
        mAnimator.setDuration(3000);
        mAnimator.setEvaluator(new ArgbEvaluator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                mTvChar.setBackgroundColor(color);
            }
        });
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);

        mCharAnimator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        mCharAnimator.setDuration(10000);
        mCharAnimator.setInterpolator(new AccelerateInterpolator());
        mCharAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char) animation.getAnimatedValue();
                mTvChar.setText(String.valueOf(text));
            }
        });
        mCharAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    public void initListener() {
        super.initListener();
        mBtnStart.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start :
                flag = !flag;
                if(flag) {
                    mBtnStart.setText("取消");
                    mAnimator.start();
                    mCharAnimator.start();
                }else {
                    mBtnStart.setText("开始");
                    mAnimator.cancel();
                    mCharAnimator.cancel();
                }
                break;
        }
    }

    public class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int)startValue;
            int endInt = (int)endValue;
            int curInt = (int) (startInt + fraction*(endInt - startInt));
            char result = (char) curInt;
            return result;
        }
    }
}
