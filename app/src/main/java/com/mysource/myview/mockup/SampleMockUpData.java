package com.mysource.myview.mockup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysource.myview.model.AccountDashBoardModel;
import com.mysource.myview.model.ChartItemModel;
import com.mysource.myview.model.CurrentAccountDetailModel;
import com.mysource.myview.model.DemoData;
import com.mysource.myview.model.FDAccountDetailModel;
import com.mysource.myview.model.GLAccountDetailModel;
import com.mysource.myview.model.LoanAccountDetailModel;
import com.mysource.myview.model.MarketModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class SampleMockUpData {

    public static List<ChartItemModel> getChartData() {
        String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.ACCOUNT_HISTORY_CHART);
        List<ChartItemModel> listData = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Type type = new TypeToken<List<ChartItemModel>>() {
            }.getType();
            listData = new Gson().fromJson(jsonObject.getString("accountHistory"), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listData;
    }

    public static List<MarketModel> getMarketList() {
        String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.MARKET_LIST_DATA);
        List<MarketModel> listData = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Type type = new TypeToken<List<MarketModel>>() {
            }.getType();
            listData = new Gson().fromJson(jsonObject.getString("stockMarket"), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listData;
    }

    public static MarketModel getMarketDetail() {
        MarketModel model = new Gson().fromJson(
                MockUpManager.getInstance().getMockJSON(MockUpManager.STOCK_MARKET_DETAIL),
                MarketModel.class);
        return model;
    }

    public static List<DemoData> getDemoList() {
        String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.DEMO_LIST);
        List<DemoData> listData;
        Type type = new TypeToken<List<DemoData>>() {
        }.getType();
        listData = new Gson().fromJson(jsonString, type);
        return listData;
    }

    public static AccountDashBoardModel getAccountDashBoardModel() {

        try {
            String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.ACCOUNT_DATA);
            JSONObject jsonObject = new JSONObject(jsonString);
            AccountDashBoardModel listModel = null;
            listModel = new Gson().fromJson(jsonObject.getString("accounts"), AccountDashBoardModel.class);
            return listModel;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CurrentAccountDetailModel getCurrentAccountDetailData() {

        try {
            String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.CURRENT_ACCOUNT_DETAIL_DATA);
            JSONObject jsonObject = new JSONObject(jsonString);
            CurrentAccountDetailModel model = null;
            model = new Gson().fromJson(jsonObject.getString("accountDetailData"), CurrentAccountDetailModel.class);
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FDAccountDetailModel getFDAccountDetail() {

        try {
            String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.FD_ACCOUNT_DETAIL_DATA);
            JSONObject jsonObject = new JSONObject(jsonString);
            FDAccountDetailModel model = null;
            model = new Gson().fromJson(jsonObject.getString("accountDetailData"), FDAccountDetailModel.class);
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GLAccountDetailModel getGLAccountDetail() {

        try {
            String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.GL_ACCOUNT_DETAIL_DATA);
            JSONObject jsonObject = new JSONObject(jsonString);
            GLAccountDetailModel model = null;
            model = new Gson().fromJson(jsonObject.getString("accountDetailData"), GLAccountDetailModel.class);
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LoanAccountDetailModel getLoanAccountDetail() {

        try {
            String jsonString = MockUpManager.getInstance().getMockJSON(MockUpManager.LOAN_ACCOUNT_DETAIL_DATA);
            JSONObject jsonObject = new JSONObject(jsonString);
            LoanAccountDetailModel model = null;
            model = new Gson().fromJson(jsonObject.getString("accountDetailData"), LoanAccountDetailModel.class);
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
