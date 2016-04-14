package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by tung.hoang on 2/2/2016.
 */
public class AccountTransactionHistoryHolder extends BaseHolder {

    @Bind(R.id.tvTransactionName)
    public TextView tvTransactionName;

    @Bind(R.id.imType)
    public ImageView imType;

    @Bind(R.id.tvTransactionAmount)
    public TextView tvTransactionAmount;

    @Bind(R.id.tvCurrencyCode)
    public TextView tvCurrencyCode;

    @Bind(R.id.tvDate)
    public TextView tvDate;

    @Bind(R.id.vDiv)
    public View vDiv;

    @Bind(R.id.lnMoreInfo)
    public LinearLayout lnMoreInfo;

    @Bind(R.id.imTriangle)
    public ImageView imTriangle;

    @Bind(R.id.tvSender)
    public TextView tvSender;

    @Bind(R.id.tvValueDate)
    public TextView tvValueDate;

    @Bind(R.id.tvBalance)
    public TextView tvBalance;

    @Bind(R.id.tvDescription)
    public TextView tvDescription;

    @Bind(R.id.lnDefaultLayout)
    public LinearLayout mLnDefaultLayout;
    @Bind(R.id.tvPrinciplePayment)
    public TextView mTvPrinciplePayment;
    @Bind(R.id.tvPrinciplePaymentCurrency)
    public TextView mTvPrinciplePaymentCurrency;
    @Bind(R.id.lnPrinciplePayment)
    public LinearLayout mLnPrinciplePayment;
    @Bind(R.id.tvPrincipleInterest)
    public TextView mTvPrincipleInterest;
    @Bind(R.id.tvPrincipleInterestCurrency)
    public TextView mTvPrincipleInterestCurrency;
    @Bind(R.id.tvDelinquentInterest)
    public TextView mTvDelinquentInterest;
    @Bind(R.id.tvDelinquentInterestCurrency)
    public TextView mTvDelinquentInterestCurrency;
    @Bind(R.id.lnLoanLayout)
    public LinearLayout mLnLoanLayout;

    public AccountTransactionHistoryHolder(View itemView) {
        super(itemView);
    }
}
