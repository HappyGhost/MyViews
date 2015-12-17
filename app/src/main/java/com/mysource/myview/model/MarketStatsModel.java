package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Stats of market
 * "open": 652.12,
 * "high": 689.01,
 * "low": 642.96,
 * "volume": "82,952,192",
 * "marketCap": "1.442B"
 *
 */
public class MarketStatsModel implements IModel {
    @SerializedName("open")
    public float open;

    @SerializedName("high")
    public float high;

    @SerializedName("low")
    public float low;

    @SerializedName("volume")
    public String volume;

    @SerializedName("marketCap")
    public String marketCap;
}
