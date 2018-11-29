package com.test.animator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.animator.R;
import com.test.animator.base.BaseReclerAdapter;
import com.test.animator.bean.MainItemBean;

import butterknife.ButterKnife;
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

public class MainAdapter extends BaseReclerAdapter<MainItemBean> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends ViewHolder {
        @InjectView(R.id.tv_item_name)
        TextView mTvItemName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(MainItemBean bean, int position) {
            mTvItemName.setText(bean.getName());
        }
    }
}
