package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by canihelpu on 12-02-16.
 */
public class DemoHolder extends BaseHolder {

    @Bind(R.id.tvTitle)
    public TextView tvTitle;

    @Bind(R.id.tvContent)
    public TextView tvContent;

    public DemoHolder(View itemView) {
        super(itemView);
    }
}
