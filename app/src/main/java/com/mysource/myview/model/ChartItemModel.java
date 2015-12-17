package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;
import com.mysource.myview.model.IModel;

/**
 * chart item model
 * "dateTime": "2015-10-01",
 * "value": 570
 *
 */
public class ChartItemModel implements IModel {
    @SerializedName("dateTime")
    public String dateTime;

    @SerializedName("value")
    public float value;
}
