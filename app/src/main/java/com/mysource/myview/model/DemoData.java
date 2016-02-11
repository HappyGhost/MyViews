package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by canihelpu on 12-02-16.
 */
public class DemoData implements IModel {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
