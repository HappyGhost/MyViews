package com.mysource.myview.ui.fragment;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mysource.myview.R;
import com.mysource.myview.model.AccountDetailModel;
import com.mysource.myview.model.IncomeChartModel;
import com.mysource.myview.util.Constants;
import com.mysource.myview.util.MoneyUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tung.hoang on 2/22/2016.
 */
public class BarChartFragment extends BaseFragment {

    @Bind(R.id.lnNote)
    LinearLayout mLnNote;
    @Bind(R.id.tvIncomeMoney)
    TextView mTvIncomeMoney;
    @Bind(R.id.tvSpentMoney)
    TextView mTvSpentMoney;
    @Bind(R.id.lnMoreInfo)
    LinearLayout mLnMoreInfo;
    @Bind(R.id.tvRow3)
    TextView mTvRow3;
    @Bind(R.id.tvRow2)
    TextView mTvRow2;
    @Bind(R.id.tvRow1)
    TextView mTvRow1;
    @Bind(R.id.imIncomeCol1)
    ImageView mImIncomeCol1;
    @Bind(R.id.imSpentCol1)
    ImageView mImSpentCol1;
    @Bind(R.id.tvCol1)
    TextView mTvCol1;
    @Bind(R.id.rlCol1)
    RelativeLayout mRlCol1;
    @Bind(R.id.imIncomeCol2)
    ImageView mImIncomeCol2;
    @Bind(R.id.imSpentCol2)
    ImageView mImSpentCol2;
    @Bind(R.id.tvCol2)
    TextView mTvCol2;
    @Bind(R.id.rlCol2)
    RelativeLayout mRlCol2;
    @Bind(R.id.imIncomeCol3)
    ImageView mImIncomeCol3;
    @Bind(R.id.imSpentCol3)
    ImageView mImSpentCol3;
    @Bind(R.id.tvCol3)
    TextView mTvCol3;
    @Bind(R.id.rlCol3)
    RelativeLayout mRlCol3;
    @Bind(R.id.imIncomeCol4)
    ImageView mImIncomeCol4;
    @Bind(R.id.imSpentCol4)
    ImageView mImSpentCol4;
    @Bind(R.id.tvCol4)
    TextView mTvCol4;
    @Bind(R.id.rlCol4)
    RelativeLayout mRlCol4;

    @Bind(R.id.lnIncomeSpentChart)
    LinearLayout mLnIncomeSpentChart;

    AccountDetailModel mAccountDetailModel;
    List<IncomeChartModel> mIncomeChartModels;
    View mItemSelected;

    boolean isMonthSelected = true;

    int mRowCount = 2;
    String mValueCurrency;
    long mStepValue;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bar_chart;
    }

    @Override
    protected void initView(View rootView) {
        String jsonString = getArguments().getString(Constants.EXTRA_DATA);
        mAccountDetailModel = new Gson().fromJson(jsonString, AccountDetailModel.class);
        mIncomeChartModels = mAccountDetailModel.getMonthData().getIncomeChartHisory();

        mLnNote.setVisibility(View.VISIBLE);
        mLnMoreInfo.setVisibility(View.GONE);

        analyzeData();

        final ViewTreeObserver observer = mLnIncomeSpentChart.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mLnIncomeSpentChart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mLnIncomeSpentChart.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                displayBarChart();
            }
        });
    }

    public void analyzeData() {

        double maxYValue = 0;
        double minYValue = 0;

        for (IncomeChartModel model : mIncomeChartModels) {
            if (minYValue == 0) {
                minYValue = model.getIncomeMoney();
            }
            if (model.getIncomeMoney() > maxYValue) {
                maxYValue = model.getIncomeMoney();
            }

            if (model.getSpentMoney() > maxYValue) {
                maxYValue = model.getSpentMoney();
            }

            if (model.getIncomeMoney() < minYValue) {
                minYValue = model.getIncomeMoney();
            }

            if (model.getSpentMoney() < minYValue) {
                minYValue = model.getSpentMoney();
            }
        }

        long shortStepValue = calculateRowValue(0, maxYValue);

        mTvRow1.setText("0");
        mTvRow2.setText(mContext.getString(R.string.bar_chart_row_title_format, shortStepValue, mValueCurrency));
        mTvRow3.setText(mContext.getString(R.string.bar_chart_row_title_format, shortStepValue * 2, mValueCurrency));

    }

    public void updateData() {
        isMonthSelected = false;
        String jsonString = getArguments().getString(Constants.EXTRA_DATA);
        mAccountDetailModel = new Gson().fromJson(jsonString, AccountDetailModel.class);
        mIncomeChartModels = mAccountDetailModel.getMonthData().getIncomeChartHisory();
        displayMonthData();
    }

    public void displayWeekData() {

        if (!isMonthSelected) {
            return;
        }

        if (mItemSelected != null) {
            mItemSelected.setSelected(false);
            mItemSelected = null;
        }

        mLnNote.setVisibility(View.VISIBLE);
        mLnMoreInfo.setVisibility(View.GONE);

        isMonthSelected = false;
        mIncomeChartModels = mAccountDetailModel.getWeekData().getIncomeChartHisory();
        analyzeData();
        displayBarChart();
    }

    public void displayMonthData() {

        if (isMonthSelected) {
            return;
        }

        if (mItemSelected != null) {
            mItemSelected.setSelected(false);
            mItemSelected = null;
        }
        mLnNote.setVisibility(View.VISIBLE);
        mLnMoreInfo.setVisibility(View.GONE);

        isMonthSelected = true;
        mIncomeChartModels = mAccountDetailModel.getMonthData().getIncomeChartHisory();
        analyzeData();
        displayBarChart();
    }

    private void displayBarChart() {
        int maxHeight = mLnIncomeSpentChart.getHeight();
        updateHeightView(mImIncomeCol1, (int) ((mIncomeChartModels.get(0).getIncomeMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImIncomeCol2, (int) ((mIncomeChartModels.get(1).getIncomeMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImIncomeCol3, (int) ((mIncomeChartModels.get(2).getIncomeMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImIncomeCol4, (int) ((mIncomeChartModels.get(3).getIncomeMoney() / (mStepValue * 2)) * maxHeight));

        updateHeightView(mImSpentCol1, (int) ((mIncomeChartModels.get(0).getSpentMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImSpentCol2, (int) ((mIncomeChartModels.get(1).getSpentMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImSpentCol3, (int) ((mIncomeChartModels.get(2).getSpentMoney() / (mStepValue * 2)) * maxHeight));
        updateHeightView(mImSpentCol4, (int) ((mIncomeChartModels.get(3).getSpentMoney() / (mStepValue * 2)) * maxHeight));

        mTvCol1.setText(mIncomeChartModels.get(0).getLabel());
        mTvCol2.setText(mIncomeChartModels.get(1).getLabel());
        mTvCol3.setText(mIncomeChartModels.get(2).getLabel());
        mTvCol4.setText(mIncomeChartModels.get(3).getLabel());

    }

    @OnClick({R.id.rlCol1, R.id.rlCol2, R.id.rlCol3, R.id.rlCol4})
    public void onChartItemClicked(View view) {

        if (mItemSelected != null) {
            if (mItemSelected == view)
                return;
            mItemSelected.setSelected(false);
        }

        mLnNote.setVisibility(View.GONE);
        mLnMoreInfo.setVisibility(View.VISIBLE);

        switch (view.getId()) {

            case R.id.rlCol1:
                mTvIncomeMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(0).getIncomeMoney(), 0));
                mTvSpentMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(0).getSpentMoney(), 0));
                break;

            case R.id.rlCol2:
                mTvIncomeMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(1).getIncomeMoney(), 0));
                mTvSpentMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(1).getSpentMoney(), 0));
                break;

            case R.id.rlCol3:
                mTvIncomeMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(2).getIncomeMoney(), 0));
                mTvSpentMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(2).getSpentMoney(), 0));
                break;

            case R.id.rlCol4:
                mTvIncomeMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(3).getIncomeMoney(), 0));
                mTvSpentMoney.setText(MoneyUtils.convertMoneyString(mIncomeChartModels.get(3).getSpentMoney(), 0));
                break;
        }
        mItemSelected = view;
        mItemSelected.setSelected(true);
    }

    private void updateHeightView(View v, float height) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.height = (int) height;
        v.setLayoutParams(layoutParams);
    }

    private long calculateRowValue(double min, double max) {
        long stepValue = (long) ((max - min) / mRowCount);
        String strStepValue = String.valueOf(stepValue);
        int roundUpNumber = Integer.parseInt(String.valueOf(strStepValue.charAt(0))) + 1;
        mStepValue = (long) (roundUpNumber * Math.pow(10, (strStepValue.length() - 1)));

        if (mStepValue >= 1000 && mStepValue < 1000000) {
            stepValue = mStepValue / 1000;
            mValueCurrency = "K";
        } else if (mStepValue >= 1000000 && mStepValue < 1000000000) {
            stepValue = mStepValue / 1000000;
            mValueCurrency = "M";
        } else if (mStepValue >= 1000000000) {
            stepValue = mStepValue / 1000000000;
            mValueCurrency = "B";
        }

        return stepValue;
    }

}
