package com.test.animator.activity;

import android.content.Context;
import android.content.Intent;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/11/29
 *     desc   :
 *     modify :
 * </pre>
 */

public class FrameAnimationActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_frame_animation;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, FrameAnimationActivity.class);
        context.startActivity(starter);
    }
}
