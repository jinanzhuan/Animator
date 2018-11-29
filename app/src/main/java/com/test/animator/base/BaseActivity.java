package com.test.animator.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/30
 *     desc   : 作为activity的基类
 *     modify :
 * </pre>
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntent();
        if(initState()) {
            return;
        }
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        initView();
        initListener();
        initData();
    }

    /**
     * 初始化状态，以决定是否加载布局，或者跳转到其他页面
     * @return
     */
    public boolean initState() {
        return false;
    }

    /**
     * 获取intent
     */
    public void initIntent() {}

    /**
     * 初始化布局
     */
    public void initView() {}

    /**
     * 初始化监听
     */
    public void initListener() {}

    /**
     * 初始化数据
     */
    public void initData() {}

    /**
     * 获取布局
     * @return
     */
    public abstract int getLayoutId();

}
