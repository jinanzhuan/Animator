package com.test.animator.activity;

import android.content.Context;
import android.content.Intent;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;

/**
 * Created by shuwei on 2018/12/9.
 */

public class ShapeDrawableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_shape_drawable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ShapeDrawableActivity.class);
        context.startActivity(starter);
    }
}
