package com.mysource.myview.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.model.GroupAccountModel;
import com.mysource.myview.ui.adapter.holder.ExpandedListHeaderHolder;
import com.mysource.myview.ui.adapter.holder.ExpandedListItemHolder;

import java.util.List;

/**
 * Created by canihelpu on 13-02-16.
 */
public class AccountAdapter extends ExpandableRecyclerAdapter<ExpandedListHeaderHolder, ExpandedListItemHolder> {

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
    public AccountAdapter(List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
    }

    @Override
    public ExpandedListHeaderHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        return new ExpandedListHeaderHolder(LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.item_account_header_row, parentViewGroup, false));
    }

    @Override
    public ExpandedListItemHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        return new ExpandedListItemHolder(LayoutInflater.from(childViewGroup.getContext()).inflate(R.layout.item_local_account, childViewGroup, false));
    }

    @Override
    public void onBindParentViewHolder(ExpandedListHeaderHolder parentViewHolder, int position, ParentListItem parentListItem) {
        GroupAccountModel groupAccountModel = (GroupAccountModel) parentListItem;
        parentViewHolder.bind(groupAccountModel);
    }

    @Override
    public void onBindChildViewHolder(ExpandedListItemHolder childViewHolder, int position, Object childListItem) {
        AccountModel accountModel = (AccountModel) childListItem;
        childViewHolder.bind(accountModel);
    }
}
