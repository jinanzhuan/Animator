package com.test.animator.activity;

import android.content.Context;
import android.content.Intent;

import com.test.animator.R;
import com.test.animator.base.BaseActivity;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/12/6
 *     desc   :
 *     modify :
 * </pre>
 */

public class PaintActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_paint;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, PaintActivity.class);
        context.startActivity(starter);
    }

}
