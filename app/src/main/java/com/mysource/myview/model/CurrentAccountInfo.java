package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class CurrentAccountInfo extends AccountModel {

    @SerializedName("balance")
    double balance;

    @SerializedName("compoundInterest")
    double compoundInterest;

    @SerializedName("interestRate")
    double interest;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
}
