package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.SimpleItemSearch;
import com.mysource.myview.ui.adapter.holder.SimpleItemHolder;


/**
 * Created by tung.hoang on 2/18/2016.
 */
public class SimpleItemAdapter extends SearchBaseAdapter<SimpleItemSearch, SimpleItemHolder> {

    public SimpleItemAdapter(Context context) {
        super(context);
    }

    public SimpleItemAdapter(Context context, BaseAdapterListener onItemClickListener) {
        super(context, onItemClickListener);
    }

    @Override
    public SimpleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleItemHolder(mLayoutInflater.inflate(R.layout.item_simple_list, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SimpleItemSearch itemSearch = mFilteredData.get(position);
        holder.tvContent.setText(itemSearch.getContent());
    }

}
