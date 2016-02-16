package com.mysource.myview.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tung.hoang on 1/21/2016.
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected List<T> mData;


    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View v = getView(layoutInflater, container, position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public abstract View getView(LayoutInflater layoutInflater, ViewGroup container, int position);
}
