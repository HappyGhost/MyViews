package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.model.GroupAccountModel;
import com.mysource.myview.ui.adapter.holder.BaseHeaderRowViewHolderRecycle;
import com.mysource.myview.ui.adapter.holder.BaseHolder;
import com.mysource.myview.ui.adapter.holder.BaseItemRowViewHolderRecycle;
import com.mysource.myview.ui.adapter.holder.LocalAccountHolder;
import com.mysource.myview.ui.adapter.holder.SimpleHeaderRecycleHolder;

import java.util.List;

/**
 * Created by tung.hoang on 1/25/2016.
 */
public class LocalAccountAdapter extends BaseExpandableRecycleViewAdapter<GroupAccountModel> {


    private static final int FOOTER_TYPE = 88;
    private Context mContext;
    private ItemClickListener mListener;


    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public LocalAccountAdapter(Context context, List<GroupAccountModel> parentItemList) {
        super(context, parentItemList);
        mContext = context;
    }

//    @Override
//    public LocalAccountHeaderItemHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
//        return new LocalAccountHeaderItemHolder(mContext, LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.item_header_row_view, parentViewGroup, false));
//    }

//    @Override
//    public LocalAccountHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
//        return new LocalAccountHolder(LayoutInflater.from(childViewGroup.getContext()).inflate(R.layout.item_local_account, childViewGroup, false));
//    }

    @Override
    public void bindingGroupData(BaseHeaderRowViewHolderRecycle baseHeaderRowViewHolder, GroupAccountModel headerModel, boolean isExpanded, View convertView, ViewGroup parent) {
        SimpleHeaderRecycleHolder simpleHeaderHolder = (SimpleHeaderRecycleHolder) baseHeaderRowViewHolder;
        simpleHeaderHolder.tvHeader.setText(mContext.getString(R.string.account_group_title_format, headerModel.getAccountType(), headerModel.getListModel().size()));
        if (!isExpanded) {
            simpleHeaderHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_arrow_down, 0);
        } else {
            simpleHeaderHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_arrow_up, 0);
        }
    }

    @Override
    public void bindingChildData(BaseItemRowViewHolderRecycle baseItemRowViewHolder, final Object childItem, boolean isLastChild, View convertView, ViewGroup parent) {
        AccountModel accountModel = (AccountModel) childItem;
        final LocalAccountHolder holder = (LocalAccountHolder) baseItemRowViewHolder;
        holder.bind(accountModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(holder, getChildPosition(childItem), childItem);
                }
            }
        });
    }

    @Override
    public BaseItemRowViewHolderRecycle getChildViewHolder(Context context) {
        return new LocalAccountHolder(context, BaseItemRowViewHolderRecycle.getViewFromResource(context, R.layout.item_local_account, null));
    }

    @Override
    public BaseHeaderRowViewHolderRecycle getGroupViewHolder(Context context) {
        View view = SimpleHeaderRecycleHolder.getViewFromResource(context, R.layout.item_header_row_view, null);
        return new SimpleHeaderRecycleHolder(context, view);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {

        }else{
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == FOOTER_TYPE) {
            return new BaseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_empty, viewGroup, false));
        }
        return super.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public void setItemListener(ItemClickListener listener) {
        mListener = listener;
    }

    public interface ItemClickListener {
        void onItemClicked(RecyclerView.ViewHolder viewHolder, int position, Object object);
    }
}
