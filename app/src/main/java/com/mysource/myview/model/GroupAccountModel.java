package com.mysource.myview.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 1/22/2016.
 */
public class GroupAccountModel implements ParentListItem, HeaderModel {

    @SerializedName("accountType")
    String accountType;

    @SerializedName("accountList")
    List<AccountModel> listModel;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<AccountModel> getListModel() {
        return listModel;
    }

    public void setListModel(List<AccountModel> listModel) {
        this.listModel = listModel;
    }

    @Override
    public List<?> getChildItemList() {
        return listModel;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public List getChildList() {
        return listModel;
    }
}
