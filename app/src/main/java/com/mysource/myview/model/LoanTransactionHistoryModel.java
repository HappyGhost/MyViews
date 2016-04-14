package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/25/2016.
 */
public class LoanTransactionHistoryModel extends AccountTransactionHistoryModel {

    @SerializedName("interest")
    double interest;

    @SerializedName("principlePayment")
    double principlePayment;

    @SerializedName("delinquentInterest")
    double delinquentInterest;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrinciplePayment() {
        return principlePayment;
    }

    public void setPrinciplePayment(double principlePayment) {
        this.principlePayment = principlePayment;
    }

    public double getDelinquentInterest() {
        return delinquentInterest;
    }

    public void setDelinquentInterest(double delinquentInterest) {
        this.delinquentInterest = delinquentInterest;
    }
}
