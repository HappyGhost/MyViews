package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class AccountDetailChartModel implements IModel{

    @SerializedName("accountChartData")
    List<ChartItemModel> chartHistory;

    @SerializedName("incomeChartData")
    List<IncomeChartModel> incomeChartHisory;

    public List<ChartItemModel> getChartHistory() {
        return chartHistory;
    }

    public void setChartHistory(List<ChartItemModel> chartHistory) {
        this.chartHistory = chartHistory;
    }

    public List<IncomeChartModel> getIncomeChartHisory() {
        return incomeChartHisory;
    }

    public void setIncomeChartHisory(List<IncomeChartModel> incomeChartHisory) {
        this.incomeChartHisory = incomeChartHisory;
    }
}
