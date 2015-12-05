package com.mysource.myview.ui.adapter.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.model.MarketModel;
import com.mysource.myview.ui.widget.chart.ChartView;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;

/**
 * Created by Tung.Hoang on 05-12-15.
 */
public class MarketHolder extends BaseHolder{

    @Bind(R.id.name_text)
    public TextView tvName;

    @Bind(R.id.buy_text)
    public TextView tvIndex;

    @Bind(R.id.percent_text)
    public TextView tvPercent;

    @Bind(R.id.chart_view)
    public ChartView chartView;

    @Bind(R.id.header)
    public View rootView;

    public MarketHolder(View itemView) {
        super(itemView);
        chartView.changeToSmallType();
    }

    public void bindData(MarketModel model) {


        tvName.setText(model.name);
        tvIndex.setText(MoneyUtils.formatReadableValue(model.value, 2));
        tvPercent.setText(String.format("%s%%", MoneyUtils.formatReadableValue(Math.abs(model.percentChanged), 2)));

        Context context = tvPercent.getContext();

        chartView.setData(model.history);

        if (model.percentChanged < 0) {
            int color = context.getResources().getColor(R.color.colorMarketDown);
            tvPercent.setTextColor(color);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_market_down);
            tvPercent.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            chartView.setStrokeColor(color, color, 0);

        } else {
            int color = context.getResources().getColor(R.color.colorMarketUp);
            tvPercent.setTextColor(color);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_market_up);
            tvPercent.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            chartView.setStrokeColor(color, color, 0);
        }
    }
}
