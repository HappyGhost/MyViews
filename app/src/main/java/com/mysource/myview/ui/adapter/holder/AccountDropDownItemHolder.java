package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;
import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by tung.hoang on 2/24/2016.
 */
public class AccountDropDownItemHolder extends BaseHolder {

    @Bind(R.id.tvAccountType)
    public TextView mTvAccountType;
    @Bind(R.id.tvAccountNumber)
    public TextView mTvAccountNumber;

    public AccountDropDownItemHolder(View itemView) {
        super(itemView);
    }
}
