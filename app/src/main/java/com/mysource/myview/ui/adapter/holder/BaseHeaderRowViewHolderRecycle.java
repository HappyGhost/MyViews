package com.mysource.myview.ui.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import butterknife.ButterKnife;

/**
 * Created by thong.nguyen on 1/15/2016.
 * Version: 1.0
 */
public class BaseHeaderRowViewHolderRecycle extends ParentViewHolder {
    Context mContext;
    View mRootView;

    public BaseHeaderRowViewHolderRecycle(Context context, View view) {
        super(view);
        mContext = context;
        mRootView = view;
        ButterKnife.bind(this, mRootView);
        mRootView.setTag(this);
    }

    public static View getViewFromResource(Context context, int contentViewId, ViewGroup viewGroup) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View rootView = mInflater.inflate(contentViewId, viewGroup, false);
        return rootView;
    }

    public View getView() {
        return mRootView;
    }
}
