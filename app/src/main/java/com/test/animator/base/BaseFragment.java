package com.test.animator.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/8/7
 *     desc   : 作为fragment的基类
 *     modify :
 * </pre>
 */

public abstract class BaseFragment extends Fragment {
    public BaseActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutId(), null);
        ButterKnife.inject(this, view);
        initArgument();
        initView();
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (BaseActivity) context;
    }

    /**
     * 获取布局ID
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化传递的参数
     */
    public void initArgument() {}

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

}
