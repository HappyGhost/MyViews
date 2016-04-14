package com.mysource.myview.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class CompanyAccountModel implements ParentListItem, HeaderModel {

    @SerializedName("accountList")
    List<AccountModel> accountList;

    @SerializedName("company")
    String companyName;

    @Override
    public List<?> getChildItemList() {
        return accountList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public List<AccountModel> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountModel> accountList) {
        this.accountList = accountList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public List getChildList() {
        return accountList;
    }
}
