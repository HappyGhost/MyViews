package com.mysource.myview.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.model.AccountDetailModel;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.model.LoanAccountDetailModel;
import com.mysource.myview.ui.adapter.AccountCarouselAdapter;
import com.mysource.myview.ui.adapter.AccountTransactionHistoryAdapter;
import com.mysource.myview.ui.adapter.BaseAdapterListener;
import com.mysource.myview.ui.adapter.FragmentAdapter;
import com.mysource.myview.ui.adapter.holder.AccountTransactionHistoryHolder;
import com.mysource.myview.ui.widget.CirclePageIndicator;
import com.mysource.myview.ui.widget.LinearLayoutManager;
import com.mysource.myview.util.Constants;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @date: Jan.20.2016
 * <br/>
 * <p/>
 * Account's Details Fragment which has represented to show the account's details including the current-account, saving-account
 * Integrating its content into the Account's Activity screen. By tapping onto the tab's item into the TabLayout of Account Activity,
 * navigate to the Account's Detail Fragment.
 */
public class AccountDetailsFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView rvAccountDetail;

    @Bind(R.id.carouselPager)
    ViewPager carouselPager;

    @Bind(R.id.indicator)
    CirclePageIndicator pageIndicator;

    @Bind(R.id.tvMonthData)
    TextView tvMonthData;

    @Bind(R.id.rlCarousel)
    RelativeLayout rlCarousel;

    @Bind(R.id.lnWeekMonthData)
    LinearLayout lnWeekMonthData;

    @Bind(R.id.tvGreeting)
    TextView tvGreeting;

    AccountDetailModel mAccountDetailModel;

    FragmentAdapter mFragmentAdapter;

    public static final String TAG = AccountDetailsFragment.class.getSimpleName();

    ChartViewFragment mChartViewFragment;
    BarChartFragment mBarChartFragment;
    TextView tvDataTypeSelected;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account_detail;
    }

    @Override
    protected void initView(View rootView) {
        AccountModel accountModel = new Gson().fromJson(getArguments().getString(Constants.EXTRA_DATA), AccountModel.class);
        rvAccountDetail.setLayoutManager(new LinearLayoutManager(mContext));

        pageIndicator.setFillColor(ContextCompat.getColor(mContext, R.color.white));
        pageIndicator.setStrokeColor(ContextCompat.getColor(mContext, R.color.white));

        callAccountDetailAPI(accountModel);
    }

    public void callAccountDetailAPI(AccountModel accountModel) {

        switch (accountModel.getType()) {

            //Current account
            case 1:
                mAccountDetailModel = SampleMockUpData.getCurrentAccountDetailData();
                initCarouselData(0);
                setupTransactionHistoryData(0);
                break;

            //FD account
            case 2:
                mAccountDetailModel = SampleMockUpData.getFDAccountDetail();
                initCarouselData(0);
                setupTransactionHistoryData(0);
                break;

            //GL account
            case 3:
                mAccountDetailModel = SampleMockUpData.getGLAccountDetail();
                initCarouselData(0);
                setupTransactionHistoryData(0);
                break;

            //Loan account
            case 4:
                mAccountDetailModel = SampleMockUpData.getLoanAccountDetail();
                initCarouselData(1);
                setupTransactionHistoryData(1);
                break;
        }
    }

    public void initCarouselData(int type) {

        switch (type) {

            //default
            case 0:
                lnWeekMonthData.setVisibility(View.VISIBLE);
                tvGreeting.setText("");
                rlCarousel.getLayoutParams().height = (int) mContext.getResources().getDimension(R.dimen.account_detail_carousel_default);
                rlCarousel.requestLayout();
                if (mFragmentAdapter == null) {
                    mFragmentAdapter = new FragmentAdapter(getChildFragmentManager());
                }

                if (pageIndicator.isViewPagerAttached()) {
                    pageIndicator.setCurrentItem(0);
                    carouselPager.setAdapter(mFragmentAdapter);
                } else {
                    carouselPager.setAdapter(mFragmentAdapter);
                    pageIndicator.setViewPager(carouselPager);
                }


                addFragments(mFragmentAdapter);
                if (tvDataTypeSelected != null) {
                    tvDataTypeSelected.setText(tvDataTypeSelected.getText().toString());
                }
                tvDataTypeSelected = tvMonthData;
                setUnderlineTextView(tvDataTypeSelected);
                mFragmentAdapter.notifyDataSetChanged();
                break;

            //Carousel of Loan account
            case 1:
                lnWeekMonthData.setVisibility(View.GONE);
                rlCarousel.getLayoutParams().height = (int) mContext.getResources().getDimension(R.dimen.account_detail_carousel_loan_account);
                rlCarousel.requestLayout();

                AccountCarouselAdapter adapter = new AccountCarouselAdapter();
                adapter.setData(((LoanAccountDetailModel) mAccountDetailModel).getCarouselModel());
                tvGreeting.setText(((LoanAccountDetailModel) mAccountDetailModel).getDashboardTitle());
                if (pageIndicator.isViewPagerAttached()) {
                    pageIndicator.setCurrentItem(0);
                    carouselPager.setAdapter(adapter);
                } else {
                    carouselPager.setAdapter(adapter);
                    pageIndicator.setViewPager(carouselPager);
                }
                break;
        }

    }

    public void setupTransactionHistoryData(int type) {
        AccountTransactionHistoryAdapter adapter = new AccountTransactionHistoryAdapter(mContext, new BaseAdapterListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof AccountTransactionHistoryHolder) {
                    AccountTransactionHistoryHolder accountTransactionHistoryHolder = (AccountTransactionHistoryHolder) holder;
                    if (accountTransactionHistoryHolder.lnMoreInfo.getVisibility() == View.VISIBLE) {
                        accountTransactionHistoryHolder.lnMoreInfo.setVisibility(View.GONE);
                        accountTransactionHistoryHolder.imTriangle.setVisibility(View.GONE);
                        accountTransactionHistoryHolder.vDiv.setVisibility(View.VISIBLE);
                    } else {
                        accountTransactionHistoryHolder.lnMoreInfo.setVisibility(View.VISIBLE);
                        accountTransactionHistoryHolder.imTriangle.setVisibility(View.VISIBLE);
                        accountTransactionHistoryHolder.vDiv.setVisibility(View.GONE);
                    }

                }
            }
        });

        adapter.setData(mAccountDetailModel);
        rvAccountDetail.setAdapter(adapter);

        switch (type) {

            //default
            case 0:
                break;

            // Loan account
            case 1:
                break;
        }
    }

    public void updateAccountData(AccountModel accountModel) {
        callAccountDetailAPI(accountModel);
    }


    protected void addFragments(FragmentAdapter fragmentAdapter) {
        if (mChartViewFragment == null) {
            mChartViewFragment = new ChartViewFragment();
            Bundle chartViewBundle = new Bundle();
            chartViewBundle.putString(Constants.EXTRA_DATA, new Gson().toJson(mAccountDetailModel));
            mChartViewFragment.setArguments(chartViewBundle);
            fragmentAdapter.addFragments(mChartViewFragment);
        } else {
            mChartViewFragment.getArguments().putString(Constants.EXTRA_DATA, new Gson().toJson(mAccountDetailModel));
            mChartViewFragment.updateData();
        }

        if (mBarChartFragment == null) {
            mBarChartFragment = new BarChartFragment();
            Bundle barChartBundle = new Bundle();
            barChartBundle.putString(Constants.EXTRA_DATA, new Gson().toJson(mAccountDetailModel));
            mBarChartFragment.setArguments(barChartBundle);

            fragmentAdapter.addFragments(mBarChartFragment);
        } else {
            mBarChartFragment.getArguments().putString(Constants.EXTRA_DATA, new Gson().toJson(mAccountDetailModel));
            mBarChartFragment.updateData();
        }
    }

    @OnClick(R.id.tvWeekData)
    public void onWeekTextCLicked(View v) {
        if (tvDataTypeSelected == v) {
            return;
        }
        tvDataTypeSelected.setText(tvDataTypeSelected.getText().toString());
        tvDataTypeSelected = (TextView) v;
        setUnderlineTextView(tvDataTypeSelected);
        mChartViewFragment.displayWeekData();
        mBarChartFragment.displayWeekData();
    }

    @OnClick(R.id.tvMonthData)
    public void onMonthTextClicked(View v) {
        if (tvDataTypeSelected == v) {
            return;
        }
        tvDataTypeSelected.setText(tvDataTypeSelected.getText().toString());
        tvDataTypeSelected = (TextView) v;
        setUnderlineTextView(tvDataTypeSelected);
        mChartViewFragment.displayMonthData();
        mBarChartFragment.displayMonthData();
    }

    private void setUnderlineTextView(TextView text) {
        SpannableString content = new SpannableString(text.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        text.setText(content);
    }


}
