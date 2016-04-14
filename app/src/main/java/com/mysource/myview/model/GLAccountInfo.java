package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/25/2016.
 */
public class GLAccountInfo extends AccountModel {

    @SerializedName("latestTransactionDate")
    String latestTransactionDate;

    @SerializedName("openingDayBalance")
    double openingDayBalance;

    @SerializedName("compoundInterest")
    double compoundInterest;

    @SerializedName("compoundIncome")
    double compoundIncome;

    @SerializedName("balance")
    double balance;

    public String getLatestTransactionDate() {
        return latestTransactionDate;
    }

    public void setLatestTransactionDate(String latestTransactionDate) {
        this.latestTransactionDate = latestTransactionDate;
    }

    public double getOpeningDayBalance() {
        return openingDayBalance;
    }

    public void setOpeningDayBalance(double openingDayBalance) {
        this.openingDayBalance = openingDayBalance;
    }

    public double getCompoundInterest() {
        return compoundInterest;
    }

    public void setCompoundInterest(double compoundInterest) {
        this.compoundInterest = compoundInterest;
    }

    public double getCompoundIncome() {
        return compoundIncome;
    }

    public void setCompoundIncome(double compoundIncome) {
        this.compoundIncome = compoundIncome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
