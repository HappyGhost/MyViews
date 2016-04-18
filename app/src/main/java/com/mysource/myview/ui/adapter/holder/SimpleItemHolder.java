package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by tung.hoang on 2/18/2016.
 */
public class SimpleItemHolder extends BaseHolder {

    @Bind(R.id.tvContent)
    public TextView tvContent;

    public SimpleItemHolder(View itemView) {
        super(itemView);
    }
}
