package com.mysource.myview.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockScrollViewGroup extends android.widget.RelativeLayout {

    public LockScrollViewGroup(Context context) {
        super(context);
    }

    public LockScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LockScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)
            requestDisallowInterceptTouchEvent(true);
        else
            requestDisallowInterceptTouchEvent(false);

        return super.onInterceptTouchEvent(ev);
    }
}