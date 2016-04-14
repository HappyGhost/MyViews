package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 1/27/2016.
 */
public class AccountDetailModel implements IModel {

    @SerializedName("weekData")
    AccountDetailChartModel weekData;

    @SerializedName("monthData")
    AccountDetailChartModel monthData;

    public AccountDetailChartModel getWeekData() {
        return weekData;
    }

    public void setWeekData(AccountDetailChartModel weekData) {
        this.weekData = weekData;
    }

    public AccountDetailChartModel getMonthData() {
        return monthData;
    }

    public void setMonthData(AccountDetailChartModel monthData) {
        this.monthData = monthData;
    }

    public List<AccountTransactionHistoryModel> getTransactionList() {
        return null;
    }
}
