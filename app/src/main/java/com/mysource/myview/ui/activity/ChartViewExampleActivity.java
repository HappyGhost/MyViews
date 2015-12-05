package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.ui.adapter.BaseAdapter;
import com.mysource.myview.ui.adapter.MarketAdapter;
import com.mysource.myview.ui.widget.DividerItemDecoration;
import com.mysource.myview.ui.widget.LinearLayoutManager;
import com.mysource.myview.ui.widget.chart.AccountSummaryChart;

import butterknife.Bind;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class ChartViewExampleActivity extends BaseActivity{

    @Bind(R.id.accountSummaryChart)
    AccountSummaryChart mChartView;

    @Bind(R.id.recyclerView)
    RecyclerView rvStockMarket;

    protected DividerItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view_example);

        init();
    }

    private void init() {

        mChartView.setStrokeColor(
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorWhite),
                getResources().getColor(R.color.colorChartAccountEndBackground)
        );
        mChartView.setMaxCol(4);
        mChartView.setTextColor(getResources().getColor(R.color.white));
        mChartView.setRowOffset(0);
        mChartView.setPopupValue(AccountSummaryChart.PopupValue.VALUE);
        mChartView.setData(SampleMockUpData.getChartData());

        rvStockMarket.setLayoutManager(new LinearLayoutManager(mContext));
        itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setIsFullEndLine(false);
        itemDecoration.setIsFullStartLine(false);
        itemDecoration.setShowOffset(true);
        rvStockMarket.addItemDecoration(itemDecoration);

        MarketAdapter adapter = new MarketAdapter(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter adapter, int position, View v) {

            }
        });
        adapter.setData(SampleMockUpData.getMarketList());
        rvStockMarket.setAdapter(adapter);

    }
}