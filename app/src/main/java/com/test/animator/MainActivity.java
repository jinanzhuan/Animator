package com.test.animator;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.test.animator.activity.DrawableActivity;
import com.test.animator.activity.FrameAnimationActivity;
import com.test.animator.activity.PaintActivity;
import com.test.animator.activity.ShapeDrawableActivity;
import com.test.animator.activity.TweenAnimationActivity;
import com.test.animator.activity.ValueAnimatorActivity;
import com.test.animator.activity.XfermodeActivity;
import com.test.animator.adapter.DividerGridItemDecoration;
import com.test.animator.adapter.MainAdapter;
import com.test.animator.base.BaseActivity;
import com.test.animator.bean.MainItemBean;
import com.test.animator.port.OnItemClickListener;

import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    @InjectView(R.id.rv_list)
    RecyclerView mRvList;
    private MainAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        mRvList.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRvList.addItemDecoration(new DividerGridItemDecoration(mContext, R.drawable.divider_grid, true));
        mAdapter = new MainAdapter(mContext);
        mRvList.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        List<MainItemBean> list = MainItemBean.getMainItemList();
        mAdapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0 :
                FrameAnimationActivity.actionStart(mContext);
                break;
            case 1 :
                TweenAnimationActivity.actionStart(mContext);
                break;
            case 2 :
                ValueAnimatorActivity.actionStart(mContext);
                break;
            case 3 :
                DrawableActivity.actionStart(mContext);
                break;
            case 4 :
                PaintActivity.actionStart(mContext);
                break;
            case 5 :
                XfermodeActivity.actionStart(mContext);
                break;
            case 6 :
                ShapeDrawableActivity.actionStart(mContext);
                break;
            default:
                Toast.makeText(MainActivity.this, "敬请期待！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
