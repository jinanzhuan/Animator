package com.test.animator.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2017/07/12
 *     desc   :
 *     modify :
 * </pre>
 */

public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    public List<T> mData = new ArrayList<>();
    public Context mContext;

    public BaseListViewAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = mData.get(position);
        BaseViewHolder holder = null;
        if(convertView == null){
            holder = getHolder();
        }else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        //加载数据
        holder.setData(t, position);
        return holder.getRootView();
    }

    /**
     * 得到ViewHolder
     * @return
     */
    public abstract BaseViewHolder getHolder();

    public List<T> getmData() {
        return mData;
    }

    /**
     * 为Adapter设置数据
     * @param mData
     */
    public void setmData(List<T> mData) {
        if(mData != null) {
            this.mData = mData;
            notifyDataSetChanged();
        }
    }

    /**
     * 清除数据
     */
    public void clearData() {
        if(mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 加载更多数据
     * @param mLists
     */
    public void loadMoreData(List<T> mLists) {
        if (null == mLists || mLists.size() == 0) {
            return;
        }

        if(mData != null) {
            mData.addAll(mLists);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加某个元素
     * @param t
     */
    public void addData(T t) {
        if(mData != null) {
            mData.add(t);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加某个位置的元素
     * @param position
     * @param t
     */
    public void addData(int position, T t) {
        if(mData != null) {
            if(position < mData.size()) {
                mData.add(position, t);
            }else {
                mData.add(t);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 移除某个元素
     * @param position
     */
    public void remove(int position) {
        if(mData != null && mData.size() > position) {
            mData.remove(position);
            notifyDataSetChanged();
        }
    }
}