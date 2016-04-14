package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.model.AccountDashBoardModel;
import com.mysource.myview.model.AccountModel;
import com.mysource.myview.model.GroupAccountModel;
import com.mysource.myview.ui.adapter.AccountDropDownAdapter;
import com.mysource.myview.ui.adapter.BaseAdapterListener;
import com.mysource.myview.ui.fragment.AccountDetailsFragment;
import com.mysource.myview.ui.widget.DividerItemDecoration;
import com.mysource.myview.ui.widget.LinearLayoutManager;
import com.mysource.myview.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tung.hoang on 1/26/2016.
 */
public class AccountDetailActivity extends BaseActivity {

    @Bind(R.id.title_text)
    TextView tvTitle;

    @Bind(R.id.rvAccount)
    RecyclerView mRvAccount;

    @Bind(R.id.flAccountList)
    FrameLayout mFlAccountList;

    @Bind(R.id.title_bar_line)
    View mTitleBarLine;

    @Bind(R.id.vLineArrow)
    View mVLineArrow;

    AccountDetailsFragment mAccountDetailsFragment;
    AccountDashBoardModel mAccountDashBoardModel;
    AccountDropDownAdapter mAccountDropDownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        mAccountDashBoardModel = SampleMockUpData.getAccountDashBoardModel();
        AccountModel accountModel = mAccountDashBoardModel.getLocalAccountList().get(0).getListModel().get(0);
        tvTitle.setText(accountModel.getAccountType());
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_arrow_down, 0);
        tvTitle.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.text_view_drawable_padding));
        mAccountDetailsFragment = new AccountDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_DATA, new Gson().toJson(accountModel));
        mAccountDetailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mAccountDetailsFragment).commit();

        setupDataForDropDownList();
    }

    private void setupDataForDropDownList() {
        List<AccountModel> accountModelList = new ArrayList<>();
        for (GroupAccountModel groupAccountModel : mAccountDashBoardModel.getLocalAccountList()) {

            for (AccountModel accountModel : groupAccountModel.getListModel()) {
                accountModelList.add(accountModel);
            }
        }
        mAccountDropDownAdapter = new AccountDropDownAdapter(mContext, new BaseAdapterListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, int position) {
                mFlAccountList.setVisibility(View.GONE);
                mVLineArrow.setVisibility(View.GONE);
                mTitleBarLine.setVisibility(View.VISIBLE);
                AccountModel accountModel = mAccountDropDownAdapter.getItem(position);
                tvTitle.setText(accountModel.getAccountType());
                mAccountDetailsFragment.updateAccountData(accountModel);
            }
        });
        mAccountDropDownAdapter.setData(accountModelList);
        mRvAccount.setLayoutManager(new LinearLayoutManager(mContext));
        mRvAccount.setAdapter(mAccountDropDownAdapter);
        mRvAccount.addItemDecoration(DividerItemDecoration.getFullDividerItem(mContext));
    }

    @OnClick(R.id.close_button)
    public void onCloseButtonClicked() {
        finish();
    }

    @OnClick(R.id.title_text)
    public void onTitleClicked() {
        if (mFlAccountList.getVisibility() == View.VISIBLE) {
            mFlAccountList.setVisibility(View.GONE);
            mVLineArrow.setVisibility(View.GONE);
            mTitleBarLine.setVisibility(View.VISIBLE);
        } else {
            mFlAccountList.setVisibility(View.VISIBLE);
            mVLineArrow.setVisibility(View.VISIBLE);
            mTitleBarLine.setVisibility(View.GONE);
        }
    }
}
