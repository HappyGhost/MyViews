package com.mysource.myview.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysource.myview.R;
import com.mysource.myview.model.ActionModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tung.Hoang on 12-12-15.
 */
public class FloatActionView extends BaseViewGroup {

    private static final long ACTION_BUTTON_START_OFFSET = 40;
    private static final int SUBTRACT_DURATION = 0;

    @Bind(R.id.btnAction)
    protected ImageView btnAction;

    @Bind(R.id.lnMenu)
    protected LinearLayout lnMenu;

    protected FloatActionListener mFloatActionListener;

    protected List<ActionModel> mListActionModel;

    private Animation mActionShowAnim, mActionHideAnim, mListActionItemAnim[],
            mActionBackgroundShowAnim, mActionBackgroundHideAnim;

    protected boolean isDataChanged = false;
    private boolean mShown = false;

    public FloatActionView(Context context) {
        super(context);
    }

    public FloatActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatActionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void init(Context context) {
        super.init(context);
        mListActionModel = new ArrayList<>();
        loadFloatAction();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_float_action;
    }

    @OnClick(R.id.btnAction)
    public void onBtnActionClicked() {

        if (isDataChanged) {
            setupAnim();
            isDataChanged = false;
        }

        if (mShown) {
            offListAction();
        } else {
            showListAction();
        }

        mFloatActionListener.onFloatActionClicked(mShown);
    }

    public void setListAction(List<ActionModel> listAction) {
        mListActionModel = listAction;
    }

    public void addAction(ActionModel actionModel) {
        mListActionModel.add(actionModel);
        addFloatActionItem(actionModel, mListActionModel.size() - 1);
        isDataChanged = true;
    }

    public void loadFloatAction() {
        lnMenu.removeAllViews();
        for (int i = 0; i < mListActionModel.size(); i++) {
            addFloatActionItem(mListActionModel.get(i), i);
        }
    }

    private void addFloatActionItem(ActionModel actionModel, final int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_float_action, null, false);
        ImageView imAction = (ImageView) v.findViewById(R.id.imAction);
        if (actionModel.getActionResource() != 0) {
            imAction.setImageResource(actionModel.getActionResource());
        }
        TextView tvAction = (TextView) v.findViewById(R.id.tvAction);
        tvAction.setText(actionModel.getActionText());
        imAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFloatActionListener != null) {
                    mFloatActionListener.onFloatActionItemClicked(view, position);
                }
            }
        });
        lnMenu.addView(v, 0, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setupAnim() {
        mActionHideAnim = AnimationUtils.loadAnimation(mContext, R.anim.dashboard_action_hide);
        mActionShowAnim = AnimationUtils.loadAnimation(mContext, R.anim.dashboard_action_show);
        mActionBackgroundShowAnim = AnimationUtils.loadAnimation(mContext, R.anim.action_background_show);
        mActionBackgroundHideAnim = AnimationUtils.loadAnimation(mContext, R.anim.action_background_hide);

        mActionBackgroundHideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lnMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mListActionItemAnim = new Animation[lnMenu.getChildCount()];
        int actionCount = lnMenu.getChildCount();
        for (int i = 0; i < actionCount; i++) {
            mListActionItemAnim[i] = AnimationUtils.loadAnimation(mContext, R.anim.action_button_show);
            long offset = (actionCount - i) * ACTION_BUTTON_START_OFFSET - (actionCount - i - 1) * SUBTRACT_DURATION;
            mListActionItemAnim[i].setStartOffset(offset);
        }
    }

    private void showListAction() {
        btnAction.startAnimation(mActionShowAnim);
        lnMenu.setVisibility(View.VISIBLE);
        lnMenu.startAnimation(mActionBackgroundShowAnim);
        for (int i = 0; i < mListActionItemAnim.length; i++) {
            lnMenu.getChildAt(i).startAnimation(mListActionItemAnim[i]);
        }

        mShown = true;
    }

    private void offListAction() {
        btnAction.startAnimation(mActionHideAnim);
        lnMenu.startAnimation(mActionBackgroundHideAnim);
        mShown = false;
    }

    public void setFloatActionListener(FloatActionListener floatActionListener) {
        mFloatActionListener = floatActionListener;
    }

    public interface FloatActionListener {
        void onFloatActionItemClicked(View view, int position);
        void onFloatActionClicked(boolean isShown);
    }

}
