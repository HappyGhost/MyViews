package com.mysource.myview.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public abstract class BaseAdapter<T,V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected List<T> mData;
    protected OnItemClickListener mOnItemClickListener;
    protected BaseAdapter mBaseAdapter;

    public BaseAdapter(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
        mBaseAdapter = this;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(V holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(mBaseAdapter, position, v);
            }
        });
    }

    public T getItem(int position){
        return mData.get(position);
    }

    public void setData(List<T> data){
        mData = data;
    }

    public interface OnItemClickListener{
        public void onItemClick(BaseAdapter adapter, int position, View v);
    }
}
