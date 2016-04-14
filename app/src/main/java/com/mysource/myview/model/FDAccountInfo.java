package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/24/2016.
 */
public class FDAccountInfo extends AccountModel{

    @SerializedName("depositDate")
    String depositDate;

    @SerializedName("totalBalance")
    double totalBalance;

    @SerializedName("compoundInterest")
    double compoundInterest;

    @SerializedName("interestRate")
    double interest;

    @SerializedName("balance")
    double balance;

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getCompoundInterest() {
        return compoundInterest;
    }

    public void setCompoundInterest(double compoundInterest) {
        this.compoundInterest = compoundInterest;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
