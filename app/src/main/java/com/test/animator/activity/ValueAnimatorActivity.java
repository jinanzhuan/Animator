package com.test.animator.activity;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

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
    @InjectView(R.id.iv_phone)
    ImageView mIvPhone;
    @InjectView(R.id.btn_keyframe_start)
    Button mBtnKeyframeStart;

    private boolean flag;
    private ValueAnimator mAnimator;
    private ValueAnimator mCharAnimator;
    private Map<String, Boolean> mMap;

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
        initKeyFrameAnimator();
    }

    private void initKeyFrameAnimator() {
        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.13f, -20f);
        Keyframe keyframe3 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe keyframe4 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe keyframe5 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe keyframe6 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe keyframe7 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe keyframe8 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe keyframe9 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe keyframe10 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe keyframe11 = Keyframe.ofFloat(1f, 0);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("Rotation"
                , keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6, keyframe7, keyframe8, keyframe9, keyframe10, keyframe11);

        Keyframe scaleF1 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleF2 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleF3 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleF4 = Keyframe.ofFloat(1f, 1);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("ScaleX", scaleF1, scaleF2, scaleF3, scaleF4);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("ScaleY", scaleF1, scaleF2, scaleF3, scaleF4);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mIvPhone, holder, scaleX, scaleY);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
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
        mBtnKeyframeStart.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mMap = new HashMap<>();
        mMap.put("123", true);
        mMap.put("1234", true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                flag = !flag;
                if (flag) {
                    mBtnStart.setText("取消");
                    mAnimator.start();
                    mCharAnimator.start();
                } else {
                    mBtnStart.setText("开始");
                    mAnimator.cancel();
                    mCharAnimator.cancel();
                }
                Boolean aBoolean = mMap.get("234");
                Boolean aBoolean2 = mMap.get("123");
                Log.e("TAG", "aBoolean2=" + aBoolean2 + " aBoolean=" + aBoolean);
                break;
            case R.id.btn_keyframe_start:

                break;
        }
    }

    public class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int) startValue;
            int endInt = (int) endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            char result = (char) curInt;
            return result;
        }
    }
}
