package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 1/22/2016.
 */
public class AccountModel implements IModel {

    @SerializedName("name")
    String accountName;

    @SerializedName("accountNumber")
    String accountNumber;

    @SerializedName("accountType")
    String accountType;

    @SerializedName("endDate")
    String endDate;

    @SerializedName("availableMoney")
    double availableMoney;

    @SerializedName("currencyCode")
    String currencyCode;

    @SerializedName("address")
    String accountAddress;

    @SerializedName("accountTypeNumber")
    int type;

    @SerializedName("isActive")
    boolean isActive;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(double availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
