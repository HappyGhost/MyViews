package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by tung.hoang on 2/2/2016.
 */
public class SimpleHeaderListHolder extends BaseHolder {

    @Bind(R.id.tvHeader)
    public TextView tvHeader;

    public SimpleHeaderListHolder(View itemView) {
        super(itemView);
    }
}
