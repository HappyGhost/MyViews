package com.mysource.myview.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysource.myview.R;
import com.mysource.myview.model.AccountDetailModel;
import com.mysource.myview.model.AccountTransactionHistoryModel;
import com.mysource.myview.model.LoanTransactionHistoryModel;
import com.mysource.myview.ui.adapter.holder.AccountTransactionHistoryHolder;
import com.mysource.myview.ui.adapter.holder.BaseHolder;
import com.mysource.myview.ui.adapter.holder.HeaderTransactionListHolder;
import com.mysource.myview.ui.adapter.holder.SimpleHeaderListHolder;
import com.mysource.myview.util.DateTimeUtils;
import com.mysource.myview.util.MoneyUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tung.hoang on 2/2/2016.
 */
public class AccountTransactionHistoryAdapter extends BaseAdapter<AccountTransactionHistoryModel, RecyclerView.ViewHolder> {

    public final static int HEADER = 3;
    public final static int FOOTER = 4;

    public final static int TAB_ALL = 1;
    public final static int TAB_INCOME = 2;
    public final static int TAB_EXPENSE = 3;

    private int mTabSelected = TAB_ALL;

    private AccountDetailModel mAccountDetailModel;
    private List<AccountTransactionHistoryModel> mDisplayedAllItemList;
    private List<AccountTransactionHistoryModel> mDisplayedIncomeList;
    private List<AccountTransactionHistoryModel> mDisplayedExpenseList;

    private List<AccountTransactionHistoryModel> mAllItemList;
    private List<AccountTransactionHistoryModel> mIncomeList;
    private List<AccountTransactionHistoryModel> mExpenseList;
    private Date mFromDate = null;
    private Date mToDate = null;

    public AccountTransactionHistoryAdapter(Context context) {
        super(context);
    }

    public AccountTransactionHistoryAdapter(Context context, BaseAdapterListener onItemClickListener) {
        super(context, onItemClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case AccountTransactionHistoryModel.TRANSACTION_HEADER:
                return new SimpleHeaderListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_simple_header_type, parent, false));

            case AccountTransactionHistoryModel.TRANSACTION_ITEM:
                return new AccountTransactionHistoryHolder(LayoutInflater.from(mContext).inflate(R.layout.item_account_transaction_history, parent, false));

            case HEADER:
                return new HeaderTransactionListHolder(LayoutInflater.from(mContext).inflate(R.layout.header_account_transaction_history, parent, false));

            case FOOTER:
                return new BaseHolder(LayoutInflater.from(mContext).inflate(R.layout.footer_empty, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == getItemCount() - 1) {
            return FOOTER;
        }
        AccountTransactionHistoryModel model = mData.get(position - 1);
        return model.getTransactionType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {

            final HeaderTransactionListHolder headerHolder = (HeaderTransactionListHolder) holder;

            switch (mTabSelected) {
                case TAB_ALL:
                    headerHolder.selectAllTab(mContext);
                    break;

                case TAB_INCOME:
                    headerHolder.selectIncomeTab(mContext);
                    break;

                case TAB_EXPENSE:
                    headerHolder.selectExpenseTab(mContext);
                    break;
            }

            headerHolder.tvAllTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTabSelected == TAB_ALL) {
                        return;
                    }
                    mTabSelected = TAB_ALL;
                    mData = mDisplayedAllItemList;
                    notifyDataSetChanged();
                }
            });

            headerHolder.tvExpenseTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTabSelected == TAB_EXPENSE) {
                        return;
                    }
                    mTabSelected = TAB_EXPENSE;
                    mData = mDisplayedExpenseList;
                    notifyDataSetChanged();
                }
            });

            headerHolder.tvIncomeTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTabSelected == TAB_INCOME) {
                        return;
                    }
                    mTabSelected = TAB_INCOME;
                    mData = mDisplayedIncomeList;
                    notifyDataSetChanged();
                }
            });

//            headerHolder.lnDateFilterFrom.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mTimeSquareDialog = new TimeSquareDialog(mContext);
//                    mTimeSquareDialog.setModeSelect(RobotoCalendarView.MODE_START_DATE);
//                    mTimeSquareDialog.show(new Date(), mFromDate, null, mTimeSquareListener);
//
//                }
//            });
//
//            headerHolder.lnDateFilterTo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mFromDate != null) {
//                        mTimeSquareDialog = new TimeSquareDialog(mContext);
//                        mTimeSquareDialog.setModeSelect(RobotoCalendarView.MODE_END_DATE);
//                        mTimeSquareDialog.show(new Date(), mFromDate, mToDate, mTimeSquareListener);
//                    }
//                }
//            });
//
//            if (mTimeSquareListener == null) {
//                mTimeSquareListener = new TimeSquareDialog.TimeSquareDialogListener() {
//                    @Override
//                    public void onSelectStartDate(Date date) {
//                    }
//
//                    @Override
//                    public void onSelectEndDate(Date date) {
//
//                    }
//
//                    @Override
//                    public void onReset() {
//
//
//                    }
//
//                    @Override
//                    public void onFinishSelect(RobotoCalendarView robotoCalendarView) {
//                    }
//
//                    @Override
//                    public void onDismiss(RobotoCalendarView robotoCalendarView) {
//                        if (robotoCalendarView.getStartDay() == null && robotoCalendarView.getEndDay() == null) {
//                            headerHolder.tvFromDate.setText("");
//                            headerHolder.tvToDate.setText("");
//                            mFromDate = null;
//                            mToDate = null;
//                            doFilter();
//                        } else if (robotoCalendarView.getStartDay() != null && robotoCalendarView.getEndDay() != null) {
//                            mFromDate = robotoCalendarView.getStartDay();
//                            mToDate = robotoCalendarView.getEndDay();
//                            headerHolder.tvFromDate.setText(DateTimeUtils.convertServerTime(mFromDate, DateTimeUtils.MONTH_DAY_YEAR_PATTERN));
//                            headerHolder.tvToDate.setText(DateTimeUtils.convertServerTime(mToDate, DateTimeUtils.MONTH_DAY_YEAR_PATTERN));
//                            doFilter();
//                        }
//                    }
//                };
//            }

            headerHolder.bind(mAccountDetailModel, mContext);

        } else if (position == getItemCount() - 1) {
            // do nothing
        } else {
            int dataPosition = position - 1;
            AccountTransactionHistoryModel model = mData.get(dataPosition);
            switch (model.getTransactionType()) {

                case AccountTransactionHistoryModel.TRANSACTION_HEADER:
                    SimpleHeaderListHolder headerItemHolder = (SimpleHeaderListHolder) holder;
                    headerItemHolder.tvHeader.setText(model.getDate());
                    break;

                case AccountTransactionHistoryModel.TRANSACTION_ITEM:
                    AccountTransactionHistoryHolder itemHolder = (AccountTransactionHistoryHolder) holder;
                    itemHolder.tvTransactionName.setText(model.getTransactionName());
                    itemHolder.tvDate.setText(DateTimeUtils.convertServerTime(model.getDate(), DateTimeUtils.DATE_MONTH_DATE_PATTERN));
                    double amount = model.getTransactionAmount();
                    if (amount > 0) {
                        itemHolder.imType.setImageResource(R.drawable.plus);
                        itemHolder.tvTransactionAmount.setTextColor(ContextCompat.getColor(mContext, R.color.rateIncreaseColor));
                    } else {
                        itemHolder.imType.setImageResource(R.drawable.minus);
                        itemHolder.tvTransactionAmount.setTextColor(ContextCompat.getColor(mContext, R.color.colorSemiBlack));

                    }
                    itemHolder.tvTransactionAmount.setText(MoneyUtils.convertMoneyString(Math.abs(amount), 0));
                    itemHolder.tvCurrencyCode.setText(model.getCurrencyCode());

                    if (dataPosition == mData.size() - 1) {
                        itemHolder.vDiv.setVisibility(View.GONE);
                    } else if (mData.get(dataPosition + 1).getTransactionType() == AccountTransactionHistoryModel.TRANSACTION_HEADER) {
                        itemHolder.vDiv.setVisibility(View.GONE);
                    } else {
                        itemHolder.vDiv.setVisibility(View.VISIBLE);
                    }

                    if (model instanceof LoanTransactionHistoryModel) {
                        LoanTransactionHistoryModel loanTransactionModel = (LoanTransactionHistoryModel) model;
                        itemHolder.mLnDefaultLayout.setVisibility(View.GONE);
                        itemHolder.mLnLoanLayout.setVisibility(View.VISIBLE);

                        itemHolder.mTvPrinciplePayment.setText(MoneyUtils.convertMoneyString(loanTransactionModel.getPrinciplePayment(), 0));
                        itemHolder.mTvPrincipleInterest.setText(MoneyUtils.convertMoneyString(loanTransactionModel.getInterest(), 0));
                        itemHolder.mTvDelinquentInterest.setText(MoneyUtils.convertMoneyString(loanTransactionModel.getDelinquentInterest(), 0));
                    } else {
                        itemHolder.mLnDefaultLayout.setVisibility(View.VISIBLE);
                        itemHolder.mLnLoanLayout.setVisibility(View.GONE);
                        itemHolder.tvSender.setText(model.getSender());
                        itemHolder.tvValueDate.setText(DateTimeUtils.convertServerTime(model.getValueDate(), DateTimeUtils.BIRTH_DATE_PATTERN));
                        itemHolder.tvBalance.setText(MoneyUtils.convertMoneyString(model.getBalance(), 0));
                        itemHolder.tvDescription.setText(model.getDescription());
                    }


                    break;
            }
            super.onBindViewHolder(holder, position);
        }
    }

    protected void doFilter() {
        mDisplayedAllItemList.clear();
        mDisplayedExpenseList.clear();
        mDisplayedIncomeList.clear();

        if (mFromDate == null) {

            mDisplayedAllItemList = addHeaderItems(mAllItemList);
            mDisplayedExpenseList = addHeaderItems(mExpenseList);
            mDisplayedIncomeList = addHeaderItems(mIncomeList);
        } else {
            for (AccountTransactionHistoryModel model : mAllItemList) {
                Date modelData = DateTimeUtils.getDateFromString(model.getDate());
                if (modelData.after(mFromDate) && modelData.before(mToDate)) {
                    mDisplayedAllItemList.add(model);
                    double amount = model.getTransactionAmount();
                    if (amount > 0) {
                        mDisplayedIncomeList.add(model);
                    } else {
                        mDisplayedExpenseList.add(model);
                    }
                }
            }
            mDisplayedAllItemList = addHeaderItems(mDisplayedAllItemList);
            mDisplayedExpenseList = addHeaderItems(mDisplayedExpenseList);
            mDisplayedIncomeList = addHeaderItems(mDisplayedIncomeList);
        }


        updateFilterData(mTabSelected);
    }

    protected void updateFilterData(int selectedTab) {

        switch (selectedTab) {

            case TAB_ALL:
                mData = mDisplayedAllItemList;
                break;

            case TAB_EXPENSE:
                mData = mDisplayedExpenseList;
                break;

            case TAB_INCOME:
                mData = mDisplayedIncomeList;
                break;
        }
        mTabSelected = selectedTab;
        notifyDataSetChanged();
    }

    public void setData(AccountDetailModel accountDetailModel) {
        mAccountDetailModel = accountDetailModel;
        mTabSelected = TAB_ALL;

        setUpData();
    }

    private void setUpData() {
        mAllItemList = mAccountDetailModel.getTransactionList();
        mIncomeList = new ArrayList<>();
        mExpenseList = new ArrayList<>();
        for (AccountTransactionHistoryModel model : mAllItemList) {
            double amount = model.getTransactionAmount();
            if (amount > 0) {
                mIncomeList.add(model);
            } else {
                mExpenseList.add(model);
            }
        }

        mDisplayedAllItemList = addHeaderItems(mAllItemList);
        mDisplayedIncomeList = addHeaderItems(mIncomeList);
        mDisplayedExpenseList = addHeaderItems(mExpenseList);

        mData = mDisplayedAllItemList;
        mTabSelected = TAB_ALL;

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 2;
    }

    protected List<AccountTransactionHistoryModel> addHeaderItems(List<AccountTransactionHistoryModel> listTransaction) {
        List<AccountTransactionHistoryModel> result = new ArrayList<>();
        AccountTransactionHistoryModel headerModel = null;
        String headerDate = "";
        for (AccountTransactionHistoryModel model : listTransaction) {

            if (headerModel == null) {
                headerModel = new AccountTransactionHistoryModel();
                headerModel.setTransactionType(AccountTransactionHistoryModel.TRANSACTION_HEADER);
            }

            if (headerDate.isEmpty()) {

                headerDate = DateTimeUtils.convertServerTime(model.getDate(), DateTimeUtils.MONTH_YEAR_PATTERN);
                headerModel.setDate(headerDate);
                result.add(headerModel);
                headerModel = null;
            } else {
                String itemDate = DateTimeUtils.convertServerTime(model.getDate(), DateTimeUtils.MONTH_YEAR_PATTERN);
                if (!headerDate.equalsIgnoreCase(itemDate)) {
                    headerDate = itemDate;
                    headerModel.setDate(headerDate);
                    result.add(headerModel);
                    headerModel = null;
                }

            }
            result.add(model);
        }

        return result;
    }
}
