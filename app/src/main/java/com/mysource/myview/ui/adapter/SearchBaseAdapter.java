package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.mysource.myview.model.ISearchModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by tung.hoang on 12/3/2015.
 */
public abstract class SearchBaseAdapter<T extends ISearchModel, K extends RecyclerView.ViewHolder> extends BaseAdapter<T, K> implements Filterable {

    List<T> mFilteredData;
    protected SearchFilter mSearchFilter;

    public SearchBaseAdapter(Context context) {
        super(context);
        mSearchFilter = new SearchFilter();
    }

    public SearchBaseAdapter(Context context, BaseAdapterListener onItemClickListener) {
        super(context, onItemClickListener);
        mSearchFilter = new SearchFilter();
    }


    public interface SearchFilterable {
        String getStringForSearching();
    }


    @Override
    public void onBindViewHolder(final K holder, final int position) {

        final T model = mData.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(holder, position);
            }
        });
    }

    public void setData(List<T> data) {
        mData = data;
    }


    public int getItemCount() {
        return mFilteredData == null ? 0 : mFilteredData.size();
    }

    public T getFilterdItem(int pos) {
        if (mFilteredData == null) {
            return null;
        }
        return mFilteredData.get(pos);
    }

    public int getRealPositionFromFilterdPosition(int position) {
        return mData.indexOf(getFilterdItem(position));
    }

    @Override
    public SearchFilter getFilter() {
        return mSearchFilter;
    }

    public class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String searchString = constraint.toString().toLowerCase(Locale.getDefault());
            FilterResults result = new FilterResults();

            List<T> resultData = new ArrayList<>();
            for (int i = 0; i < mData.size(); i++) {

                T model = mData.get(i);
                if (model.getStringForSearching().toLowerCase(Locale.getDefault()).contains(searchString)) {
                    resultData.add(model);
                }
            }
            result.values = resultData;
            result.count = resultData.size();

            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results == null) {
                mFilteredData.clear();
            } else {
                mFilteredData = (List<T>) results.values;
            }
            notifyDataSetChanged();
        }
    }

}
