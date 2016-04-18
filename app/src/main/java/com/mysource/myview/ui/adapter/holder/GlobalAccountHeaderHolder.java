package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class GlobalAccountHeaderHolder extends BaseHolder {

    @Bind(R.id.tvSelectedLocation)
    public TextView tvSelectedLocation;

    public GlobalAccountHeaderHolder(View itemView) {
        super(itemView);
    }

}
