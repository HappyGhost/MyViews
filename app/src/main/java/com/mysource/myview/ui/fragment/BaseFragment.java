package com.mysource.myview.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysource.myview.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * All fragments will extends from this
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    //Save the rootview to destroy on onDestroyView event happen to avoid memory leak
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setRetainInstance(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, mRootView);
        initView(mRootView);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        mRootView = null;
        super.onDestroyView();
    }

    protected abstract int getLayoutResId();

    protected abstract void initView(View rootView);

    public void showKeyboard() {
        ((BaseActivity) getActivity()).showKeyboard();
    }

    public void hideKeyboard() {
        ((BaseActivity) getActivity()).hideSoftKeyboard();
    }
}
