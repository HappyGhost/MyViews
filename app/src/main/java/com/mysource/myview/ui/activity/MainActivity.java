package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mysource.myview.R;
import com.mysource.myview.mockup.SampleMockUpData;
import com.mysource.myview.ui.adapter.BaseAdapterListener;
import com.mysource.myview.ui.adapter.DemoAdapter;
import com.mysource.myview.ui.widget.DividerItemDecoration;
import com.mysource.myview.ui.widget.LinearLayoutManager;
import com.mysource.myview.util.ActivityHelper;

import butterknife.Bind;

/**
 * Created by canihelpu on 12-02-16.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    DemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new DemoAdapter(mContext);
        mAdapter.setData(SampleMockUpData.getDemoList());
        mAdapter.setOnItemClickListener(new BaseAdapterListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, int position) {
                switch (position) {
                    case 0:
                        ActivityHelper.openChartViewExample(mContext);
                        break;

                    case 1:
                        ActivityHelper.openFLoatActionExample(mContext);
                        break;

                    case 2:
                        ActivityHelper.openCycleAnimationExample(mContext);
                        break;

                    case 3:
                        break;
                }
            }
        });
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setIsFullEndLine(true);
        itemDecoration.setIsFullStartLine(false);
        itemDecoration.setMargin(
                (int) getResources().getDimension(R.dimen.recycler_padding),
                0, 0, 0
        );
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(mAdapter);
    }
}
