package com.mysource.myview.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.ui.adapter.SearchBaseAdapter;
import com.mysource.myview.ui.widget.DividerItemDecoration;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tung.hoang on 12/3/2015.
 */
public class SearchListDialog extends BaseDialog {

    @Bind(R.id.title_text)
    TextView tvTitle;

    @Bind(R.id.recycler_view)
    RecyclerView rvList;

    @Bind(R.id.btnBack)
    ImageView imBack;

    @Bind(R.id.edtSearch)
    EditText edtSearch;

    private DividerItemDecoration mDecoration;
    private int mTitleResId = 0;
    private SearchBaseAdapter mAdapter;

    public SearchListDialog(Context context) {
        super(context);
    }

    public SearchListDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SearchListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (mTitleResId != 0)
            tvTitle.setText(mTitleResId);

        init();
        initEvent();
    }

    public  int getLayoutId(){
        return R.layout.dialog_list_with_search;
    }

    protected void init() {
        //init recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        mDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        mDecoration.setIsFullEndLine(true);
        mDecoration.setIsFullStartLine(false);
        mDecoration.setShowOffset(false);
        mDecoration.setMargin(
                (int) getContext().getResources().getDimension(R.dimen.recycler_padding),
                0, 0, 0
        );
        rvList.addItemDecoration(mDecoration);

        if (mAdapter != null) {
            rvList.setAdapter(mAdapter);
        }
        imBack.setVisibility(View.GONE);
    }

    protected void initEvent() {

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setTitle(int resId) {
        mTitleResId = resId;
        if (tvTitle != null)
            tvTitle.setText(mTitleResId);
    }

    @OnClick(R.id.cancel_button)
    public void onCancelClicked() {
        dismiss();
    }

    public void setAdapter(SearchBaseAdapter adapter) {
        mAdapter = adapter;

        if (rvList != null) {
            mDecoration.setEndLine(mAdapter.getItemCount() - 1);
            rvList.setAdapter(mAdapter);
        }
        mAdapter.getFilter().filter("");
    }

    public SearchBaseAdapter getAdapter(){
        return mAdapter;
    }

    public void setBackVisibility(int visibility) {
        imBack.setVisibility(visibility);
    }

}
