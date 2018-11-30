package com.test.animator.activity;

import android.content.Context;
import android.content.Intent;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;

/**
 * Created by ljn on 2018/11/29.
 */

public class DrawableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_drawable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, DrawableActivity.class);
        context.startActivity(starter);
    }
}
