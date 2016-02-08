package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public abstract class BaseAdapter<T, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {
    protected BaseAdapterListener mOnItemClickListener;
    protected List<T> mData;
    protected BaseAdapter mAdapter;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;

    public BaseAdapter(Context context) {
        mAdapter = this;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public BaseAdapter(Context context, BaseAdapterListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        mAdapter = this;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(BaseAdapterListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    protected void onItemClicked(K holder, int pos) {
        mOnItemClickListener.onItemClicked(mAdapter, holder, pos);
    }

    @Override
    public void onBindViewHolder(final K holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(holder, position);
            }
        });
    }

    public T getItem(int pos) {
        return mData.get(pos);
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
    }

    public void add(T item) {
        if (mData != null) {
            mData.add(item);
        }
    }
}
