package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.DemoData;
import com.mysource.myview.ui.adapter.holder.DemoHolder;

/**
 * Created by canihelpu on 12-02-16.
 */
public class DemoAdapter extends BaseAdapter<DemoData, DemoHolder> {

    public DemoAdapter(Context context) {
        super(context);
    }

    public DemoAdapter(Context context, BaseAdapterListener onItemClickListener) {
        super(context, onItemClickListener);
    }

    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoHolder(mLayoutInflater.inflate(R.layout.item_demo, parent, false));
    }

    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DemoData demoData = mData.get(position);
        holder.tvTitle.setText(demoData.getTitle());
        holder.tvContent.setText(demoData.getDescription());
    }
}
