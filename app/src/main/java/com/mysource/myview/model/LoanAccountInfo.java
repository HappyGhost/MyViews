package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/25/2016.
 */
public class LoanAccountInfo extends AccountModel {

    @SerializedName("dueDate")
    String dueDate;

    @SerializedName("principleAmount")
    double principle;

    @SerializedName("compoundInterest")
    double compoundInterest;

    @SerializedName("interest")
    double interest;

    @SerializedName("principlePayment")
    double principlePayment;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getPrinciple() {
        return principle;
    }

    public void setPrinciple(double principle) {
        this.principle = principle;
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

    public double getPrinciplePayment() {
        return principlePayment;
    }

    public void setPrinciplePayment(double principlePayment) {
        this.principlePayment = principlePayment;
    }
}
