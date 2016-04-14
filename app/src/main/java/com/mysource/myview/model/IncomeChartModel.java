package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class IncomeChartModel implements IModel {

    @SerializedName("label")
    String label;

    @SerializedName("income")
    double incomeMoney;

    @SerializedName("spent")
    double spentMoney;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
