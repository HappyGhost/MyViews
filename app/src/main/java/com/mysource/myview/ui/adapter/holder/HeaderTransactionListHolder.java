package com.mysource.myview.ui.adapter.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountDetailModel;
import com.mysource.myview.model.CurrentAccountDetailModel;
import com.mysource.myview.model.FDAccountDetailModel;
import com.mysource.myview.model.GLAccountDetailModel;
import com.mysource.myview.model.LoanAccountDetailModel;
import com.mysource.myview.util.DateTimeUtils;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tung.hoang on 2/16/2016.
 */
public class HeaderTransactionListHolder extends BaseHolder {

    @Bind(R.id.tvAllTab)
    public TextView tvAllTab;

    @Bind(R.id.tvIncomeTab)
    public TextView tvIncomeTab;

    @Bind(R.id.tvExpenseTab)
    public TextView tvExpenseTab;

    @Bind(R.id.tvViewMore)
    public TextView tvViewMore;

    @Bind(R.id.lnViewMore)
    public LinearLayout lnViewMore;

    @Bind(R.id.imViewMore)
    public ImageView imViewMore;

    @Bind(R.id.tvAccountId)
    public TextView tvAccountId;

    @Bind(R.id.tvAccountName)
    public TextView tvAccountName;

    @Bind(R.id.tvCompanyName)
    public TextView tvCompanyName;

    @Bind(R.id.tvAccountAvailableFund)
    public TextView tvAccountAvailableFund;

    @Bind(R.id.tvBalance)
    public TextView tvBalance;

    @Bind(R.id.tvDescription1)
    public TextView tvDescription1;

    @Bind(R.id.tvDescription2)
    public TextView tvDescription2;

    @Bind(R.id.tvBalanceLabel)
    public TextView tvBalanceLabel;

    @Bind(R.id.tvDescriptionLabel1)
    public TextView tvDescriptionLabel1;

    @Bind(R.id.tvDescriptionLabel2)
    public TextView tvDescriptionLabel2;

    @Bind(R.id.tvAvailableLabel)
    public TextView tvAvailableLabel;

    @Bind(R.id.lnDateFilter)
    public LinearLayout lnDateFilter;

    @Bind(R.id.lnDateFilterFrom)
    public LinearLayout lnDateFilterFrom;

    @Bind(R.id.lnDateFilterTo)
    public LinearLayout lnDateFilterTo;

    @Bind(R.id.tvFromDate)
    public TextView tvFromDate;

    @Bind(R.id.tvToDate)
    public TextView tvToDate;

    @Bind(R.id.lnExtendedAccountInfo)
    LinearLayout lnExtendedAccountInfo;

    @Bind(R.id.tvDescriptionCurrency1)
    TextView mTvDescriptionCurrency1;

    @Bind(R.id.tvDescriptionCurrency2)
    TextView mTvDescriptionCurrency2;

    @Bind(R.id.lnSegmentControl)
    LinearLayout mLnSegmentControl;

    public HeaderTransactionListHolder(View itemView) {
        super(itemView);
    }

    @OnClick(R.id.lnViewMore)
    public void onViewMoreClicked() {

        if (lnViewMore.isSelected()) {
            tvViewMore.setText(R.string.account_detail_text_view_more);
            imViewMore.setImageResource(R.drawable.icon_arrow_down);
            lnExtendedAccountInfo.setVisibility(View.GONE);
        } else {
            tvViewMore.setText(R.string.account_detail_text_view_less);
            imViewMore.setImageResource(R.drawable.icon_arrow_up);
            lnExtendedAccountInfo.setVisibility(View.VISIBLE);
        }
        lnViewMore.setSelected(!tvViewMore.isSelected());
    }

    @OnClick(R.id.lnFilter)
    public void onFilterButtonClicked() {
        if (lnDateFilter.getVisibility() == View.VISIBLE) {
            lnDateFilter.setVisibility(View.GONE);
        } else {
            lnDateFilter.setVisibility(View.VISIBLE);
        }
    }


    public void unSelectAllTab(Context context) {
        tvAllTab.setSelected(false);
        tvAllTab.setTextColor(ContextCompat.getColor(context, R.color.colorGreySegment));
        tvIncomeTab.setSelected(false);
        tvIncomeTab.setTextColor(ContextCompat.getColor(context, R.color.colorGreySegment));
        tvExpenseTab.setSelected(false);
        tvExpenseTab.setTextColor(ContextCompat.getColor(context, R.color.colorGreySegment));
    }

    public void selectAllTab(Context context) {
        unSelectAllTab(context);
        tvAllTab.setSelected(true);
        tvAllTab.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public void selectIncomeTab(Context context) {
        unSelectAllTab(context);
        tvIncomeTab.setSelected(true);
        tvIncomeTab.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public void selectExpenseTab(Context context) {
        unSelectAllTab(context);
        tvExpenseTab.setSelected(true);
        tvExpenseTab.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public void bind(AccountDetailModel accountDetailModel, Context context) {

        if (accountDetailModel instanceof CurrentAccountDetailModel) {
            CurrentAccountDetailModel currentAccountDetailModel = (CurrentAccountDetailModel) accountDetailModel;
            tvAccountId.setText(currentAccountDetailModel.getCurrentAccountInfo().getAccountNumber());
            tvAccountName.setText(currentAccountDetailModel.getCurrentAccountInfo().getAccountName());
            tvCompanyName.setText(currentAccountDetailModel.getCurrentAccountInfo().getAccountAddress());
            tvAccountAvailableFund.setText(MoneyUtils.convertMoneyString(currentAccountDetailModel.getCurrentAccountInfo().getAvailableMoney(), 0));

            tvBalance.setText(MoneyUtils.convertMoneyString(currentAccountDetailModel.getCurrentAccountInfo().getBalance(), 0));
            tvDescription1.setText(String.valueOf(currentAccountDetailModel.getCurrentAccountInfo().getInterest()));
            tvDescription2.setText(MoneyUtils.convertMoneyString(currentAccountDetailModel.getCurrentAccountInfo().getCompoundInterest(), 0));

            tvDescriptionLabel1.setText(R.string.label_interest_rate);
            tvDescriptionLabel2.setText(R.string.label_compound_interest);
            tvAvailableLabel.setText(R.string.label_available);
            mTvDescriptionCurrency1.setText(R.string.symbol_percent);
            tvBalanceLabel.setText(R.string.label_balance);

            mLnSegmentControl.setVisibility(View.VISIBLE);
        }

        if (accountDetailModel instanceof FDAccountDetailModel) {
            FDAccountDetailModel fdAccountDetailModel = (FDAccountDetailModel) accountDetailModel;
            tvAccountId.setText(fdAccountDetailModel.getFDAccountInfo().getAccountNumber());
            tvAccountName.setText(context.getString(R.string.account_detail_format_deposit_date, DateTimeUtils.convertServerTime(fdAccountDetailModel.getFDAccountInfo().getDepositDate(), DateTimeUtils.DAY_MONTH_YEAR_FORMAT)));
            tvCompanyName.setText(fdAccountDetailModel.getFDAccountInfo().getAccountAddress());
            tvAccountAvailableFund.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getFDAccountInfo().getTotalBalance(), 0));

            tvBalance.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getFDAccountInfo().getBalance(), 0));
            tvDescription1.setText(String.valueOf(fdAccountDetailModel.getFDAccountInfo().getInterest()));
            tvDescription2.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getFDAccountInfo().getCompoundInterest(), 0));

            tvDescriptionLabel1.setText(R.string.label_interest_rate);
            tvDescriptionLabel2.setText(R.string.label_compound_interest);
            tvAvailableLabel.setText(R.string.account_detail_total_balance_label);
            mTvDescriptionCurrency1.setText(R.string.symbol_percent);
            tvBalanceLabel.setText(R.string.label_balance);

            mLnSegmentControl.setVisibility(View.VISIBLE);
        }

        if (accountDetailModel instanceof GLAccountDetailModel) {

            GLAccountDetailModel fdAccountDetailModel = (GLAccountDetailModel) accountDetailModel;
            tvAccountId.setText(fdAccountDetailModel.getGLAccountInfo().getAccountNumber());
            tvAccountName.setText(context.getString(R.string.account_detail_format_latest_transaction_date, DateTimeUtils.convertServerTime(fdAccountDetailModel.getGLAccountInfo().getLatestTransactionDate(), DateTimeUtils.DAY_MONTH_YEAR_FORMAT)));
            tvCompanyName.setText(fdAccountDetailModel.getGLAccountInfo().getAccountAddress());
            tvAccountAvailableFund.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getGLAccountInfo().getOpeningDayBalance(), 0));
            tvBalance.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getGLAccountInfo().getBalance(), 0));
            tvDescription1.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getGLAccountInfo().getCompoundInterest(), 0));
            tvDescription2.setText(MoneyUtils.convertMoneyString(fdAccountDetailModel.getGLAccountInfo().getCompoundIncome(), 0));

            tvDescriptionLabel1.setText(R.string.label_compound_interest);
            tvDescriptionLabel2.setText(R.string.account_detail_compound_income_label);
            tvAvailableLabel.setText(R.string.account_detail_opening_day_balance_label);
            mTvDescriptionCurrency1.setText(R.string.vnd);
            tvBalanceLabel.setText(R.string.label_balance);

            mLnSegmentControl.setVisibility(View.VISIBLE);
        }

        if (accountDetailModel instanceof LoanAccountDetailModel) {

            LoanAccountDetailModel loanAccountDetailModel = (LoanAccountDetailModel) accountDetailModel;
            tvAccountId.setText(loanAccountDetailModel.getLoanAccountInfo().getAccountNumber());
            tvAccountName.setText(context.getString(R.string.account_detail_format_latest_transaction_date, DateTimeUtils.convertServerTime(loanAccountDetailModel.getLoanAccountInfo().getDueDate(), DateTimeUtils.DAY_MONTH_YEAR_FORMAT)));
            tvCompanyName.setText(loanAccountDetailModel.getLoanAccountInfo().getAccountAddress());
            tvAccountAvailableFund.setText(MoneyUtils.convertMoneyString(loanAccountDetailModel.getLoanAccountInfo().getPrinciple(), 0));
            tvBalance.setText(MoneyUtils.convertMoneyString(loanAccountDetailModel.getLoanAccountInfo().getPrinciplePayment(), 0));
            tvDescription1.setText(MoneyUtils.convertMoneyString(loanAccountDetailModel.getLoanAccountInfo().getInterest(), 0));
            tvDescription2.setText(MoneyUtils.convertMoneyString(loanAccountDetailModel.getLoanAccountInfo().getCompoundInterest(), 0));

            tvDescriptionLabel1.setText(R.string.account_detail_interest_label);
            tvDescriptionLabel2.setText(R.string.label_compound_interest);
            tvAvailableLabel.setText(R.string.account_detail_principle_label);
            mTvDescriptionCurrency1.setText(R.string.vnd);
            tvBalanceLabel.setText(R.string.account_detail_principle_payment_label);

            mLnSegmentControl.setVisibility(View.GONE);

        }
    }
}
