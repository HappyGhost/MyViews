package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class CurrentAccountDetailModel extends AccountDetailModel {

    @SerializedName("accountInfo")
    CurrentAccountInfo mCurrentAccountInfo;

    @SerializedName("transactionHistory")
    public List<AccountTransactionHistoryModel> transactionList;

    public CurrentAccountInfo getCurrentAccountInfo() {
        return mCurrentAccountInfo;
    }

    public void setCurrentAccountInfo(CurrentAccountInfo currentAccountInfo) {
        this.mCurrentAccountInfo = currentAccountInfo;
    }

    public List<AccountTransactionHistoryModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AccountTransactionHistoryModel> transactionList) {
        this.transactionList = transactionList;
    }

}
