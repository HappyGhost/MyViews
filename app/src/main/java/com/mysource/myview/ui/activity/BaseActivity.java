package com.mysource.myview.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * Created by Tung.Hoang on 30-11-15.
 */
public class BaseActivity extends FragmentActivity{

    protected Context mContext;
    private InputMethodManager mImm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mImm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ButterKnife.bind(this);
    }

    public void showKeyboard() {
        mImm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//        imm.showSoftInput(edtFrom, 0);
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(getCurrentFocus(), InputMethodManager.SHOW_FORCED);
        }
    }

    public void showKeyboard(View view) {
        view.requestFocus();
        mImm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
}
