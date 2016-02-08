package com.mysource.myview.ui.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by tung.hoang on 12/3/2015.
 */
public interface BaseAdapterListener {

    void onItemClicked(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, int position);
}
