package com.mysource.myview.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mysource.myview.R;

@SuppressWarnings("SameParameterValue")
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;
    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;
    private int mEndLinePos = Integer.MAX_VALUE;
    private boolean mIsEndLineFull = false;
    private boolean mIsFullStartLine = false;
    private boolean mIsShowOffset = true;

    private int mDivSize;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

        mDivSize = (int) (1 * context.getResources().getDisplayMetrics().density);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == mEndLinePos)
                break;

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivSize;

            int rLeft = left;
            int rRight = right;

            if ((!mIsFullStartLine || i > 0) && (!mIsEndLineFull || i < childCount - 1)) {
                rLeft += mMarginLeft;
                rRight += mMarginRight;
            }

            mDivider.setBounds(rLeft, top, rRight, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop() + mMarginTop;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() + mMarginBottom;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            if (i == mEndLinePos)
                break;

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivSize;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mIsShowOffset) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivSize);
            } else {
                outRect.set(0, 0, mDivSize, 0);
            }
        } else {
            if (itemPosition == 0 && mIsFullStartLine) {
                if (mOrientation == VERTICAL_LIST) {
                    outRect.set(0, 0, 0, mDivSize);
                } else {
                    outRect.set(0, 0, mDivSize, 0);
                }
            }
        }
    }

    public void setMargin(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginTop = top;
        mMarginRight = right;
        mMarginBottom = bottom;
    }

    public void setShowOffset(boolean isShow) {
        mIsShowOffset = isShow;
    }

    public void setEndLine(int position) {
        mEndLinePos = position;
    }

    public void setIsFullEndLine(boolean isFull) {
        mIsEndLineFull = isFull;
    }

    public void setIsFullStartLine(boolean isFull) {
        mIsFullStartLine = isFull;
    }

    public static DividerItemDecoration getDefaultDividerItem(Context context) {
        DividerItemDecoration decoration = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        decoration.setIsFullEndLine(false);
        decoration.setIsFullStartLine(false);
        decoration.setMargin(
                (int) context.getResources().getDimension(R.dimen.recycler_padding),
                0, 0, 0
        );
        return decoration;
    }

    public static DividerItemDecoration getFullDividerItem(Context context) {
        DividerItemDecoration decoration = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        decoration.setIsFullEndLine(true);
        decoration.setIsFullStartLine(true);
        return decoration;
    }
}
