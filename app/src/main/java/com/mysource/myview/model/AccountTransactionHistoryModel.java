package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tung.hoang on 2/1/2016.
 */
public class AccountTransactionHistoryModel implements IModel {

    public static final int TRANSACTION_HEADER = 1;
    public static final int TRANSACTION_ITEM = 2;

    @SerializedName("transactionName")
    String transactionName;

    @SerializedName("transactionAmount")
    double transactionAmount;

    @SerializedName("transactionCurrencyCode")
    String currencyCode;

    @SerializedName("transactionDate")
    String date;

    @SerializedName("sender")
    String sender;

    @SerializedName("valueDate")
    String valueDate;

    @SerializedName("balance")
    double balance;

    @SerializedName("description")
    String description;


    int transactionType = TRANSACTION_ITEM;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
