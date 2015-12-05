package com.mysource.myview.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.MarketModel;
import com.mysource.myview.ui.adapter.holder.MarketHolder;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class MarketAdapter extends BaseAdapter<MarketModel, MarketHolder> {

    public MarketAdapter(OnItemClickListener onItemClickListener) {
        super(onItemClickListener);
    }

    @Override
    public MarketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MarketHolder(inflater.inflate(R.layout.item_stock_market, parent, false));
    }

    @Override
    public void onBindViewHolder(MarketHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindData(mData.get(position));
    }
}
