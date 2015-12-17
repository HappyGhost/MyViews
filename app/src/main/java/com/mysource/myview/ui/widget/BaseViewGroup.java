package com.mysource.myview.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by tung.hoang on 12/8/2015.
 */
public abstract class BaseViewGroup extends LinearLayout {

    protected Context mContext;

    public BaseViewGroup(Context context) {
        super(context);
        init(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context){
        mContext = context;
        View v = LayoutInflater.from(mContext).inflate(getLayoutId(), this, false);
        addView(v);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();
}
