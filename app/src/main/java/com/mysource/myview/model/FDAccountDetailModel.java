package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/24/2016.
 */
public class FDAccountDetailModel extends AccountDetailModel {

    @SerializedName("accountInfo")
    FDAccountInfo mFDAccountInfo;

    @SerializedName("transactionHistory")
    public List<AccountTransactionHistoryModel> transactionList;

    public FDAccountInfo getFDAccountInfo() {
        return mFDAccountInfo;
    }

    public void setFDAccountInfo(FDAccountInfo FDAccountInfo) {
        mFDAccountInfo = FDAccountInfo;
    }

    public List<AccountTransactionHistoryModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AccountTransactionHistoryModel> transactionList) {
        this.transactionList = transactionList;
    }
}
