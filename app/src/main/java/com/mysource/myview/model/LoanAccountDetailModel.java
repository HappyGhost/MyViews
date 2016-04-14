package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung.hoang on 2/25/2016.
 */
public class LoanAccountDetailModel extends AccountDetailModel {

    @SerializedName("accountInfo")
    LoanAccountInfo mLoanAccountInfo;

    @SerializedName("accountTitle")
    String dashboardTitle;

    @SerializedName("carouselData")
    List<AccountCarouselModel> carouselModel;


    @SerializedName("transactionHistory")
    List<LoanTransactionHistoryModel> transactionList;

    List<AccountTransactionHistoryModel> accountTransactionList;

    public LoanAccountInfo getLoanAccountInfo() {
        return mLoanAccountInfo;
    }

    public void setLoanAccountInfo(LoanAccountInfo loanAccountInfo) {
        mLoanAccountInfo = loanAccountInfo;
    }

    public String getDashboardTitle() {
        return dashboardTitle;
    }

    public void setDashboardTitle(String dashboardTitle) {
        this.dashboardTitle = dashboardTitle;
    }

    public List<AccountCarouselModel> getCarouselModel() {
        return carouselModel;
    }

    public void setCarouselModel(List<AccountCarouselModel> carouselModel) {
        this.carouselModel = carouselModel;
    }


    public List<AccountTransactionHistoryModel> getTransactionList() {
        if (accountTransactionList == null) {
            accountTransactionList = new ArrayList<>();
            for (LoanTransactionHistoryModel model : transactionList) {
                accountTransactionList.add(model);
            }
        }
        return accountTransactionList;
    }

    public void setTransactionList(List<LoanTransactionHistoryModel> transactionList) {
        this.transactionList = transactionList;
    }
}
