package com.mysource.myview.ui.adapter.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.mysource.myview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by thong.nguyen on 1/15/2016.
 * Version: 1.0
 */
public abstract class BaseItemRowViewHolderRecycle extends ChildViewHolder {
    @Nullable
    @Bind(R.id.divLine)
    public View divLine;
    @Nullable
    @Bind(R.id.divEndLine)
    public View divEndLine;
    View mRootView;
    Context mContext;

    @SuppressLint("InflateParams")
    public BaseItemRowViewHolderRecycle(Context context, View rootView) {
        super(rootView);
        mContext = context;
        mRootView = rootView;
        ButterKnife.bind(this, mRootView);
        mRootView.setTag(this);
    }

    public static View getViewFromResource(Context context, int contentViewId, ViewGroup viewGroup) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View rootView = mInflater.inflate(R.layout.item_row_view, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.layContent);
        View contentView = mInflater.inflate(contentViewId, null, false);
        linearLayout.addView(contentView);
        return rootView;
    }

    public View getView() {
        return mRootView;
    }

}
