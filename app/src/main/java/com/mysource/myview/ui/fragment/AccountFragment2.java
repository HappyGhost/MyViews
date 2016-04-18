package com.mysource.myview.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.model.AccountDashBoardModel;
import com.mysource.myview.ui.adapter.AccountCarouselAdapter;
import com.mysource.myview.ui.adapter.GlobalAccountAdapter;
import com.mysource.myview.ui.adapter.LocalAccountAdapter;
import com.mysource.myview.ui.widget.CirclePageIndicator;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tung.hoang on 1/21/2016.
 */
public class AccountFragment2 extends BaseFragment {

    @Bind(R.id.carouselPager)
    ViewPager carouselPager;

    @Bind(R.id.indicator)
    CirclePageIndicator pageIndicator;

    @Bind(R.id.tabLocalAccount)
    TextView tabLocalAccount;

    @Bind(R.id.tabGlobalAccount)
    TextView tabGlobalAccount;

    @Bind(R.id.tvGreeting)
    TextView tvGreeting;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    LocalAccountAdapter mLocalAccountAdapter;

    GlobalAccountAdapter mGlobalAccountAdapter;

    private AccountDashBoardModel mAccountDashBoardModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account_2;
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

        selectLocalAccountTab();

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mLocalAccountAdapter = new LocalAccountAdapter(mContext, mAccountDashBoardModel.getLocalAccountList());
        mLocalAccountAdapter.setItemListener(new LocalAccountAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.ViewHolder viewHolder, int position, Object object) {
            }
        });
        mLocalAccountAdapter.setUserLineAdapter(true);
        mGlobalAccountAdapter = new GlobalAccountAdapter(mContext, mAccountDashBoardModel.getGlobalAccountList());
        mGlobalAccountAdapter.setItemListener(new LocalAccountAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.ViewHolder viewHolder, int position, Object object) {
            }
        });
        mGlobalAccountAdapter.setUserLineAdapter(true);

        recyclerView.setAdapter(mLocalAccountAdapter);

    }


    @OnClick(R.id.tabLocalAccount)
    public void onTabLocalAccountClicked() {
        if (!tabLocalAccount.isSelected()) {
            selectLocalAccountTab();
            recyclerView.setAdapter(mLocalAccountAdapter);
        }
    }

    @OnClick(R.id.tabGlobalAccount)
    public void onTabGlobalAccountClicked() {
        if (!tabGlobalAccount.isSelected()) {
            selectGlobalAccountTab();
            recyclerView.setAdapter(mGlobalAccountAdapter);
        }
    }


    public void unSelectAllTab() {
        tabLocalAccount.setSelected(false);
        tabLocalAccount.setTextColor(ContextCompat.getColor(mContext, R.color.blue_light));
        tabGlobalAccount.setSelected(false);
        tabGlobalAccount.setTextColor(ContextCompat.getColor(mContext, R.color.blue_light));
    }

    public void selectLocalAccountTab() {
        unSelectAllTab();
        tabLocalAccount.setSelected(true);
        tabLocalAccount.setTextColor(ContextCompat.getColor(mContext, R.color.white));
    }

    public void selectGlobalAccountTab() {
        unSelectAllTab();
        tabGlobalAccount.setSelected(true);
        tabGlobalAccount.setTextColor(ContextCompat.getColor(mContext, R.color.white));
    }
}
