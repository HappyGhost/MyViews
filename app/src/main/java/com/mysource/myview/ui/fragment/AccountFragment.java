package com.mysource.myview.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.model.AccountDashBoardModel;
import com.mysource.myview.ui.adapter.AccountAdapter;
import com.mysource.myview.ui.adapter.AccountCarouselAdapter;
import com.mysource.myview.ui.widget.CirclePageIndicator;

import butterknife.Bind;

/**
 * Created by tung.hoang on 1/21/2016.
 */
public class AccountFragment extends BaseFragment {

    @Bind(R.id.carouselPager)
    ViewPager carouselPager;

    @Bind(R.id.indicator)
    CirclePageIndicator pageIndicator;

    @Bind(R.id.tvGreeting)
    TextView tvGreeting;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private AccountDashBoardModel mAccountDashBoardModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView(View rootView) {

        callAccountDashBoardAPI();
    }

    public void callAccountDashBoardAPI() {

        mAccountDashBoardModel = SampleMockUpData.getAccountDashBoardModel();

        tvGreeting.setText(mAccountDashBoardModel.getDashboardTitle());
        AccountCarouselAdapter adapter = new AccountCarouselAdapter();
        adapter.setData(mAccountDashBoardModel.getCarouselModel());
        carouselPager.setAdapter(adapter);
        pageIndicator.setFillColor(getResources().getColor(R.color.colorWhite));
        pageIndicator.setStrokeColor(getResources().getColor(R.color.colorWhite));
        pageIndicator.setViewPager(carouselPager);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        AccountAdapter accountAdapter = new AccountAdapter(mAccountDashBoardModel.getListLocalAccount());
        recyclerView.setAdapter(accountAdapter);
    }

}
