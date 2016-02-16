package com.mysource.myview.mockup;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class MockUpManager {

    private static MockUpManager mInstance;
    private Resources mResources;
    private Context mContext;

    /* Key of json mock up */
    public static final String ACCOUNT_HISTORY_CHART = "accountChartList_2.json";
    public static final String MARKET_LIST_DATA = "marketListData.json";
    public static final String STOCK_MARKET_DETAIL = "stockMarketDetail.json";
    public static final String DEMO_LIST = "demoData.json";
    public static final String ACCOUNT_DATA = "accountData.json";

    private MockUpManager() {

    }

    public static MockUpManager getInstance() {
        if (mInstance == null)
            mInstance = new MockUpManager();

        return mInstance;
    }

    public void init(Context context) {
        mResources = context.getResources();
        mContext = context;
    }

    public String getMockJSON(String fileName) {
        BufferedInputStream bis = null;

        // byte array to store input
        byte[] contents = new byte[1024];
        int bytesRead;
        String s = "";
        try {
            bis = new BufferedInputStream(mResources.getAssets().open("mockdata/" + fileName));
            while ((bytesRead = bis.read(contents)) != -1) {
                s += new String(contents, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s;
    }
}
