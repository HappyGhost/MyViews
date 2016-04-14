package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.ui.adapter.holder.AccountDropDownItemHolder;


/**
 * Created by tung.hoang on 2/24/2016.
 */
public class AccountDropDownAdapter extends BaseAdapter<AccountModel, AccountDropDownItemHolder> {


    public AccountDropDownAdapter(Context context) {
        super(context);
    }

    public AccountDropDownAdapter(Context context, BaseAdapterListener onItemClickListener) {
        super(context, onItemClickListener);
    }

    @Override
    public AccountDropDownItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountDropDownItemHolder(mLayoutInflater.inflate(R.layout.item_dropdown_account, parent, false));
    }

    @Override
    public void onBindViewHolder(AccountDropDownItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        AccountModel accountModel = mData.get(position);
        holder.mTvAccountType.setText(accountModel.getAccountType());
        holder.mTvAccountNumber.setText(accountModel.getAccountNumber());
    }
}
