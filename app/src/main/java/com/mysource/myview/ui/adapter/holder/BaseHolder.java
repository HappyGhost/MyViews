package com.mysource.myview.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class BaseHolder extends RecyclerView.ViewHolder{

    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
