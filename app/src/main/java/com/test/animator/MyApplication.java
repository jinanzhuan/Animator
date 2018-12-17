package com.test.animator;

import android.app.Application;

import org.litepal.LitePal;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/12/11
 *     desc   :
 *     modify :
 * </pre>
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLitepal();
    }

    private void initLitepal() {
        LitePal.initialize(this);
    }
}
