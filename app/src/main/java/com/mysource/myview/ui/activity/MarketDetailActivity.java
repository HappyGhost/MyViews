package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.model.ChartItemModel;
import com.mysource.myview.model.MarketModel;
import com.mysource.myview.ui.widget.chart.ChartView;
import com.mysource.myview.util.DateTimeUtils;
import com.mysource.myview.util.MoneyUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Market detail screen.
 * Created by cuong.huynh on 10/7/2015.
 */
public class MarketDetailActivity extends BaseActivity implements ChartView.OnSelectedListener{

    public static final String EXTRA_MARKET_NAME = "name";

    private static final int ONE_DAY = 1;
    private static final int ONE_MONTH = 30;
    private static final int TWO_MONTHS = 60;
    private static final int THREE_MONTHS = 90;

    //title text
    @Bind(R.id.title_text)
    protected TextView mTitleText;

    //Chart
    @Bind(R.id.chart_view)
    protected ChartView mChartView;

    @Bind(R.id.index_text)
    protected TextView mIndexText;

    @Bind(R.id.index_fraction_text)
    protected  TextView mIndexFractionText;

    @Bind(R.id.index_change_text)
    protected TextView mIndexChangeText;

    @Bind(R.id.last_update_text)
    protected TextView mLastUpdateText;

    @Bind(R.id.one_day_button)
    protected Button mOneDayButton;

    @Bind(R.id.scrollview)
    protected ScrollView scrollView;

    private String mStockName;
    private int mDays = ONE_DAY;

    private View mSelectedDayButton;
    private MarketModel mModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stock_market_detail);

        //get extra value
        mStockName = getIntent().getStringExtra(EXTRA_MARKET_NAME);

        //set title
        mTitleText.setText(mStockName.toUpperCase(java.util.Locale.getDefault()));

        //highlight one day button
        mSelectedDayButton = mOneDayButton;
        setUnderlineTextView(mOneDayButton);

       // mSelectedDayButton.setSelected(true);

        mChartView.setSelectedListener(this);
        mModel = SampleMockUpData.getMarketDetail();
        bindDataToUI(mModel);
    }


    private void bindDataToUI(MarketModel data) {

        mChartView.setData(data.history);
        setIndexText(data);
        mLastUpdateText.setText(getString(R.string.text_last_updated,
                DateTimeUtils.convertServerTime(data.updatedDate, DateTimeUtils.LAST_UPDATE_DATE_TIME_PATTERN)));
    }

    private void setIndexText(MarketModel data) {
        bindIndexText(data.value);
        float changeValue = data.value / 100 * data.percentChanged;
        String textChanged = MoneyUtils.formatReadableValue(changeValue, 2) + " (" + data.percentChanged + "%)";
        if(changeValue > 0) {
            mIndexChangeText.setText(String.format("+%s", textChanged));
            mIndexChangeText.setTextColor(getResources().getColor(R.color.colorMarketUp));

            mChartView.setStrokeColor(
                    getResources().getColor(R.color.colorMarketUp),
                    getResources().getColor(R.color.colorChartUpBackground),
                    getResources().getColor(R.color.colorChartUpEndBackground)
                    );
        } else {
            mIndexChangeText.setText(String.format("-%s", textChanged));
            mIndexChangeText.setTextColor(getResources().getColor(R.color.colorMarketDown));

            mChartView.setStrokeColor(
                    getResources().getColor(R.color.colorMarketDown),
                    getResources().getColor(R.color.colorChartDownBackground),
                    getResources().getColor(R.color.colorChartDownEndBackground)
            );
        }
    }

    private void bindIndexText(float value) {
        String[] indexText = String.valueOf(value).split("\\.");
        if(indexText.length > 1){
            mIndexFractionText.setText(String.format(".%s",indexText[1]));
            mIndexFractionText.setVisibility(View.VISIBLE);
        }else{
            mIndexFractionText.setVisibility(View.GONE);
        }
        mIndexText.setText(String.format("%s", indexText[0]));
    }

    /**
     * Close activity
     */
    @OnClick(R.id.close_button)
    protected void onCloseClick() {
        finish();
    }

    @OnClick(R.id.one_day_button)
    protected void onOneDayClick(View v) {
        setChartDataView(v, ONE_DAY);
    }

    @OnClick(R.id.one_month_button)
    protected void onOneMonthClick(View v) {
        setChartDataView(v, ONE_MONTH);
    }

    @OnClick(R.id.two_months_button)
    protected void onTwoMonthsClick(View v) {
        setChartDataView(v, TWO_MONTHS);
    }

    @OnClick(R.id.three_months_button)
    protected void onThreeMonthsClick(View v) {
        setChartDataView(v, THREE_MONTHS);
    }

    private void setUnderlineTextView(Button text) {
        SpannableString content = new SpannableString(text.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        text.setText(content);
    }

    private void unSetUnderlineTextView(Button text) {
        text.setText(text.getText().toString());
    }

    private void setChartDataView(View v, int days) {
        if(!v.isSelected()) {
            //mSelectedDayButton.setSelected(false);
            unSetUnderlineTextView((Button) mSelectedDayButton);
            //highlight one day button
            mSelectedDayButton = v;
            setUnderlineTextView((Button) mSelectedDayButton);
            //v.setSelected(true);

            mDays = days;
        }
    }

    @Override
    public void onSelectedChange(int col, ChartItemModel model) {

        String textChanged = "";

        if(col > 0) {
            ChartItemModel prevModel = mModel.history.get(col - 1);
            float changeValue = model.value - prevModel.value;
            textChanged = MoneyUtils.formatReadableValue(changeValue, 2);

            if(prevModel.value != 0) {
                float changePercent = (changeValue / prevModel.value) * 100f;
                textChanged += " (" +
                        MoneyUtils.formatReadableValue(changePercent, 2) + "%)";
            }

            if(changeValue > 0) {
                mIndexChangeText.setTextColor(getResources().getColor(R.color.colorMarketUp));
            } else {
                mIndexChangeText.setTextColor(getResources().getColor(R.color.colorMarketDown));
            }
        }
        bindIndexText(model.value);
        mIndexChangeText.setText(textChanged);
    }

    @Override
    public void onUnselected() {
        setIndexText(mModel);
    }
}
