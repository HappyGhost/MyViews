package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.model.AccountCarouselModel;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;

/**
 * Created by tung.hoang on 1/22/2016.
 */
public class AccountCarouselHolder extends BaseHolder {

    @Bind(R.id.tvValue)
    TextView tvValue;

    @Bind(R.id.tvCurrencyCode)
    TextView tvCurrencyCode;

    @Bind(R.id.tvType)
    TextView tvType;

    public AccountCarouselHolder(View itemView) {
        super(itemView);
    }

    public void bindData(AccountCarouselModel model) {
        tvValue.setText(MoneyUtils.convertMoneyString(model.getAccountValue(), 0));
        tvCurrencyCode.setText(model.getCurrency());
        tvType.setText(model.getTypeName());
    }
}
