package com.mysource.myview.ui.adapter.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mysource.myview.R;

import butterknife.Bind;

/**
 * Created by nmthong on 1/16/2016.
 */
public class SimpleHeaderRecycleHolder extends BaseHeaderRowViewHolderRecycle {
    @Nullable
    @Bind(R.id.tvHeader)
    public TextView tvHeader;
    boolean isExpandable = true;

    public SimpleHeaderRecycleHolder(Context context, View view) {
        super(context, view);
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setIsExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return isExpandable;
    }
}
