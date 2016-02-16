package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.util.DateTimeUtils;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by canihelpu on 13-02-16.
 */
public class ExpandedListItemHolder extends ChildViewHolder {


    @Bind(R.id.tvAccountNumber)
    public TextView tvAccountNumber;

    @Bind(R.id.tvExpireDate)
    public TextView tvExpireDate;

    @Bind(R.id.tvAvailableFund)
    public TextView tvAvailableFund;

    @Bind(R.id.tvCurrencyCode)
    public TextView tvCurrencyCode;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public ExpandedListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(AccountModel model) {

        tvAccountNumber.setText(model.getAccountNumber());
        tvExpireDate.setText(DateTimeUtils.convertServerTime(model.getEndDate(), DateTimeUtils.SIMPLE_DATE_PATTERN));
        tvAvailableFund.setText(MoneyUtils.convertMoneyString(model.getAvailableMoney(), 0));
        tvCurrencyCode.setText(model.getCurrencyCode());
    }
}
