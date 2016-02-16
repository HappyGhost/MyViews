package com.mysource.myview.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.mysource.myview.R;
import com.mysource.myview.model.GroupAccountModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by canihelpu on 13-02-16.
 */
public class ExpandedListHeaderHolder extends ParentViewHolder {

    @Bind(R.id.tvHeader)
    public TextView tvHeader;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public ExpandedListHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(GroupAccountModel model) {
        tvHeader.setText(model.getAccountType());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_arrow_down, 0);
                    collapseView();
                } else {
                    tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_arrow_up, 0);
                    expandView();
                }
            }
        });

    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }
}
