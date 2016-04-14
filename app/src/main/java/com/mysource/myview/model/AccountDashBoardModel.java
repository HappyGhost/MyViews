package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tung.hoang on 1/22/2016.
 */
public class AccountDashBoardModel implements IModel {

    @SerializedName("accountTitle")
    String dashboardTitle;

    @SerializedName("carouselData")
    List<AccountCarouselModel> carouselModel;

    @SerializedName("localAccounts")
    List<GroupAccountModel> localAccountList;

    @SerializedName("GlobalAccounts")
    List<GlobalAccountModel> globalAccountList;

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

    public List<GroupAccountModel> getLocalAccountList() {
        return localAccountList;
    }

    public void setLocalAccountList(List<GroupAccountModel> localAccountList) {
        this.localAccountList = localAccountList;
    }

    public List<GlobalAccountModel> getGlobalAccountList() {
        return globalAccountList;
    }

    public void setGlobalAccountList(List<GlobalAccountModel> globalAccountList) {
        this.globalAccountList = globalAccountList;
    }
}
