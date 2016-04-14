package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 2/17/2016.
 */
public class GlobalAccountModel implements IModel {

    @SerializedName("city")
    String city;

    @SerializedName("cityAccountItems")
    List<CompanyAccountModel> companyAccountList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CompanyAccountModel> getCompanyAccountList() {
        return companyAccountList;
    }

    public void setCompanyAccountList(List<CompanyAccountModel> companyAccountList) {
        this.companyAccountList = companyAccountList;
    }
}
