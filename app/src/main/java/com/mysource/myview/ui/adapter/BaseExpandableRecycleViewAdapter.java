package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.mysource.myview.ui.adapter.holder.BaseHeaderRowViewHolderRecycle;
import com.mysource.myview.ui.adapter.holder.BaseItemRowViewHolderRecycle;

import java.util.Hashtable;
import java.util.List;

public abstract class BaseExpandableRecycleViewAdapter<T extends ParentListItem> extends ExpandableRecyclerAdapter {
    List<T> mArrayListParent;
    Context mContext;
    boolean mUserLineAdapter = false;
    Hashtable<Object, Integer> hashTableGroupIndex = new Hashtable<>();
    Hashtable<Object, Integer> hashTableChildIndex = new Hashtable<>();

    public BaseExpandableRecycleViewAdapter(Context context, List<T> arrayListParent) {
        super(arrayListParent);
        mContext = context;
        mArrayListParent = arrayListParent;
        List<T> list = getArrayListParent();
        for (int i = 0; i < list.size(); i++) {
            ParentListItem groupModel = list.get(i);
            List modelList = groupModel.getChildItemList();
            for (int j = 0; j < modelList.size(); j++) {
                hashTableGroupIndex.put(modelList.get(j), i);
                hashTableChildIndex.put(modelList.get(j), j);
            }
        }
    }

    public boolean isUserLineAdapter() {
        return mUserLineAdapter;
    }

    public void setUserLineAdapter(boolean userLineAdapter) {
        mUserLineAdapter = userLineAdapter;
    }

    public List<T> getArrayListParent() {
        return mArrayListParent;
    }

    public void setArrayListParent(List<T> arrayListParent) {
        mArrayListParent = arrayListParent;
    }

    @Override
    public ParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        return getGroupViewHolder(mContext);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        return getChildViewHolder(mContext);
    }

    @Override
    public void onBindParentViewHolder(ParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        T headerModel = (T) parentListItem;
        BaseHeaderRowViewHolderRecycle headerRowViewHolder = (BaseHeaderRowViewHolderRecycle) parentViewHolder;
        bindingGroupData(headerRowViewHolder, headerModel, headerRowViewHolder.isExpanded(), headerRowViewHolder.getView(), null);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int position, Object childListItem) {
        BaseItemRowViewHolderRecycle itemRowViewHolder = (BaseItemRowViewHolderRecycle) childViewHolder;
        boolean isLastChild = false;
        if (position < super.getItemCount() - 1) {
            if (super.getItemViewType(position++) != super.getItemViewType(position)) {
                isLastChild = true;
                if (mUserLineAdapter) {
                    itemRowViewHolder.divLine.setVisibility(View.GONE);
                    itemRowViewHolder.divEndLine.setVisibility(View.VISIBLE);
                } else {
                    itemRowViewHolder.divLine.setVisibility(View.GONE);

                }
            } else {
                isLastChild = false;
                if (mUserLineAdapter) {
                    itemRowViewHolder.divLine.setVisibility(View.VISIBLE);
                    itemRowViewHolder.divEndLine.setVisibility(View.GONE);
                } else {
                    itemRowViewHolder.divLine.setVisibility(View.GONE);

                }
            }
        } else {
            isLastChild = true;
            if (mUserLineAdapter) {
                itemRowViewHolder.divLine.setVisibility(View.GONE);
                itemRowViewHolder.divEndLine.setVisibility(View.VISIBLE);
            } else {
                itemRowViewHolder.divLine.setVisibility(View.GONE);

            }
        }

        bindingChildData(itemRowViewHolder, childListItem, isLastChild, itemRowViewHolder.getView(), null);

    }

    public int getGroupCount() {
        return mArrayListParent.size();
    }


    public int getChildrenCount(int groupPosition) {
        return mArrayListParent.get(groupPosition).getChildItemList().size();
    }


    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    public Object getChild(int groupPosition, int childPosition) {
        return mArrayListParent.get(groupPosition).getChildItemList().get(childPosition);
    }


    public Object getGroup(int groupPosition) {
        return mArrayListParent.get(groupPosition);
    }

    public int getGroupPosition(Object childModel) {
        if (hashTableGroupIndex.containsKey(childModel)) {
            return hashTableGroupIndex.get(childModel);
        }
        return -1;
    }

    public int getChildPosition(Object childModel) {
        if (hashTableChildIndex.containsKey(childModel)) {
            return hashTableChildIndex.get(childModel);
        }
        return -1;
    }


    @Override
    public void onParentListItemExpanded(int position) {
        super.onParentListItemExpanded(position);
        notifyItemChanged(position);
    }

    @Override
    public void onParentListItemCollapsed(int position) {
        super.onParentListItemCollapsed(position);
        notifyItemChanged(position);
    }

    public abstract void bindingGroupData(BaseHeaderRowViewHolderRecycle baseHeaderRowViewHolder, T headerModel, boolean isExpanded, View convertView, ViewGroup parent);

    public abstract void bindingChildData(BaseItemRowViewHolderRecycle baseItemRowViewHolder, Object childItem, boolean isLastChild, View convertView, ViewGroup parent);

    public abstract BaseItemRowViewHolderRecycle getChildViewHolder(Context context);

    public abstract BaseHeaderRowViewHolderRecycle getGroupViewHolder(Context context);
}
