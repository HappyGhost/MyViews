package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapterHelper;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.model.CompanyAccountModel;
import com.mysource.myview.model.GlobalAccountModel;
import com.mysource.myview.ui.adapter.holder.BaseHeaderRowViewHolderRecycle;
import com.mysource.myview.ui.adapter.holder.BaseHolder;
import com.mysource.myview.ui.adapter.holder.BaseItemRowViewHolderRecycle;
import com.mysource.myview.ui.adapter.holder.GlobalAccountHeaderHolder;
import com.mysource.myview.ui.adapter.holder.LocalAccountHolder;
import com.mysource.myview.ui.adapter.holder.SimpleHeaderRecycleHolder;
import com.mysource.myview.ui.dialog.SearchListDialog;
import com.mysource.myview.util.DialogUtils;

import java.util.List;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class GlobalAccountAdapter extends BaseExpandableRecycleViewAdapter<CompanyAccountModel> {

    private static final int HEADER_LIST_VIEW = 5;
    private static final int FOOTER_LIST_VIEW = 88;
    private Context mContext;
    private List<GlobalAccountModel> mGlobalAccountList;
    private String mSelectedLocation = "";
    private SearchListDialog mCityDialog;
    private LocalAccountAdapter.ItemClickListener mListener;

    public GlobalAccountAdapter(Context context, List<GlobalAccountModel> globalAccountList) {
        super(context, globalAccountList.get(0).getCompanyAccountList());
        mGlobalAccountList = globalAccountList;
        mContext = context;
        mItemList.add(0, null);
    }


    @Override
    public void bindingGroupData(BaseHeaderRowViewHolderRecycle baseHeaderRowViewHolder, CompanyAccountModel headerModel, boolean isExpanded, View convertView, ViewGroup parent) {
        SimpleHeaderRecycleHolder simpleHeaderHolder = (SimpleHeaderRecycleHolder) baseHeaderRowViewHolder;
        simpleHeaderHolder.tvHeader.setText(mContext.getString(R.string.account_group_title_format, headerModel.getCompanyName(), headerModel.getAccountList().size()));
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
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_LIST_VIEW;
        } else if (position == getItemCount() - 1) {
            return FOOTER_LIST_VIEW;
        }
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            final GlobalAccountHeaderHolder globalAccountHeaderHolder = (GlobalAccountHeaderHolder) holder;
            if (mSelectedLocation.isEmpty()) {
                globalAccountHeaderHolder.tvSelectedLocation.setText(mGlobalAccountList.get(0).getCity());
            } else {
                globalAccountHeaderHolder.tvSelectedLocation.setText(mSelectedLocation);
            }
            globalAccountHeaderHolder.tvSelectedLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCityDialog == null) {
                        mCityDialog = DialogUtils.getGlobalAccountFilterDialog(mContext, new BaseAdapterListener() {
                            @Override
                            public void onItemClicked(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, int position) {
                                SimpleItemAdapter simpleItemAdapter = (SimpleItemAdapter) adapter;
                                int realPosition = simpleItemAdapter.getRealPositionFromFilterdPosition(position);
                                mItemList = ExpandableRecyclerAdapterHelper.generateParentChildItemList(mGlobalAccountList.get(realPosition).getCompanyAccountList());
                                mItemList.add(0, null);

                                mSelectedLocation = simpleItemAdapter.getItem(realPosition).getContent();
                                notifyDataSetChanged();
                                mCityDialog.dismiss();
                            }
                        }, mGlobalAccountList);
                    }
                    mCityDialog.show();
                }
            });
        } else if (position == getItemCount() - 1) {

        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER_LIST_VIEW) {
            return new GlobalAccountHeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_global_account_list, viewGroup, false));
        } else if (viewType == FOOTER_LIST_VIEW) {
            return new BaseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_empty, viewGroup, false));
        } else {
            return super.onCreateViewHolder(viewGroup, viewType);
        }
    }

    public void setItemListener(LocalAccountAdapter.ItemClickListener listener) {
        mListener = listener;
    }


}
