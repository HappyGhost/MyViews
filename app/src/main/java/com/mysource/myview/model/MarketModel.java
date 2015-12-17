package com.mysource.myview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Store data of market
 */
public class MarketModel implements IModel {
//    {
//        "name": "HOSE",
//        "history": [
//        {
//            "dateTime": "2015-10-01",
//                "value": 570
//        },
//        {
//            "dateTime": "2015-10-02",
//                "value": 550
//        },
//        {
//            "dateTime": "2015-10-03",
//                "value": 570
//        }
//        ],
//        "stats": {
//                "open": 652.12,
//                "high": 689.01,
//                "low": 642.96,
//                "volume": "82,952,192",
//                "marketCap": "1.442B"
//          },
//        "value": 572.12,
//        "percentChanged": 1.59,
//        "updatedDate": "2013-04-24T22:53:03+00:00"
//    }

    @SerializedName("name")
    public String name;

    @SerializedName("history")
    public List<ChartItemModel> history;

    @SerializedName("stats")
    public MarketStatsModel stats;

    @SerializedName("value")
    public float value;

    @SerializedName("percentChanged")
    public float percentChanged;

    @SerializedName("updatedDate")
    public String updatedDate;

    @SerializedName("address")
    public String address;
}
