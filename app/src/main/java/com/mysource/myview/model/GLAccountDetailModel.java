package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/25/2016.
 */
public class GLAccountDetailModel extends AccountDetailModel {

    @SerializedName("accountInfo")
    GLAccountInfo mGLAccountInfo;

    @SerializedName("transactionHistory")
    public List<AccountTransactionHistoryModel> transactionList;

    public GLAccountInfo getGLAccountInfo() {
        return mGLAccountInfo;
    }

    public void setGLAccountInfo(GLAccountInfo GLAccountInfo) {
        mGLAccountInfo = GLAccountInfo;
    }

    public List<AccountTransactionHistoryModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AccountTransactionHistoryModel> transactionList) {
        this.transactionList = transactionList;
    }
}
