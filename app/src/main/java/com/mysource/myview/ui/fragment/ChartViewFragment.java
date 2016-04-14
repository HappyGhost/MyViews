package com.mysource.myview.ui.fragment;

import android.view.View;

import com.google.gson.Gson;
import com.mysource.myview.R;
import com.mysource.myview.model.CurrentAccountDetailModel;
import com.mysource.myview.ui.widget.chart.ChartView;
import com.mysource.myview.util.Constants;

import butterknife.Bind;

/**
 * Created by tung.hoang on 1/27/2016.
 */
public class ChartViewFragment extends BaseFragment {

    @Bind(R.id.accountSummaryChart)
    ChartView accountSummaryChart;

    CurrentAccountDetailModel mCurrentAccount;

    boolean isMonthSelected = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_chart_view;
    }

    @Override
    protected void initView(View rootView) {

        String jsonString = getArguments().getString(Constants.EXTRA_DATA);
        mCurrentAccount = new Gson().fromJson(jsonString, CurrentAccountDetailModel.class);
        accountSummaryChart.setStrokeColor(
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorWhite),
                getResources().getColor(R.color.colorChartAccountEndBackground)
        );
        accountSummaryChart.setMaxCol(4);
        accountSummaryChart.setTextColor(getResources().getColor(R.color.white));
        accountSummaryChart.setPopupValue(ChartView.PopupValue.VALUE);
        accountSummaryChart.setData(mCurrentAccount.getMonthData().getChartHistory());
        accountSummaryChart.setRowOffset(0);
        displayMonthData();
    }

    public void updateData() {
        String jsonString = getArguments().getString(Constants.EXTRA_DATA);
        isMonthSelected = false;
        mCurrentAccount = new Gson().fromJson(jsonString, CurrentAccountDetailModel.class);
        displayMonthData();
    }

    public void displayWeekData() {
        if (!isMonthSelected) {
            return;
        }
        isMonthSelected = false;
        accountSummaryChart.setData(mCurrentAccount.getWeekData().getChartHistory());
    }

    public void displayMonthData() {
        if (isMonthSelected) {
            return;
        }
        isMonthSelected = true;
        accountSummaryChart.setData(mCurrentAccount.getMonthData().getChartHistory());
    }

}
