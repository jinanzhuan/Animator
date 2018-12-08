package com.test.animator.activity;

import android.content.Context;
import android.content.Intent;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;

/**
 * Created by shuwei on 2018/12/8.
 */

public class XfermodeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_xfermode;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, XfermodeActivity.class);
        context.startActivity(starter);
    }
}
