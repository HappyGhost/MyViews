package com.mysource.myview.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.util.DateTimeUtils;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;

/**
 * Created by tung.hoang on 1/25/2016.
 */
public class LocalAccountHolder extends BaseItemRowViewHolderRecycle {

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
    public LocalAccountHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public void bind(AccountModel accountModel) {
        tvAccountNumber.setText(accountModel.getAccountNumber());
        tvExpireDate.setText(DateTimeUtils.convertServerTime(accountModel.getEndDate(), DateTimeUtils.SIMPLE_DATE_PATTERN));
        tvAvailableFund.setText(MoneyUtils.convertMoneyString(accountModel.getAvailableMoney(), 0));
        tvCurrencyCode.setText(accountModel.getCurrencyCode());
    }
}
