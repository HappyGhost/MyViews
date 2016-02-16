package com.mysource.myview.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.AccountCarouselModel;
import com.mysource.myview.ui.adapter.holder.AccountCarouselHolder;


/**
 * Created by tung.hoang on 1/21/2016.
 */
public class AccountCarouselAdapter extends BasePagerAdapter<AccountCarouselModel> {

    @Override
    public View getView(LayoutInflater layoutInflater, ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.item_account_carousel, container, false);
        AccountCarouselModel model = mData.get(position);
        AccountCarouselHolder holder = new AccountCarouselHolder(v);
        holder.bindData(model);
        return v;
    }
}
