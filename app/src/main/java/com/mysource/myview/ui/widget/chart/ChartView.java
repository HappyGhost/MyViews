package com.mysource.myview.ui.widget.chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.mysource.myview.R;
import com.mysource.myview.model.ChartItemModel;
import com.mysource.myview.util.TypeFaceManager;

import java.util.List;

public class ChartView extends View {

    public static final int CHART_TYPE_TYPE_FULL = 0;
    public static final int CHART_TYPE_TYPE_SMALL = 1;

    private static final int[] VALUE_ROW_STEP = {1, 2, 3, 4, 5};
    private static final int CHART_STROKE_COLOR = 0xff828383;
    private static final int CHART_LINE_COLOR = 0xaadddddd;
    private static final int CHART_COLOR = 0xfffdce0d;
    private static final int CHART_BACKGROUND_COLOR = 0x66fdce0d;

    private static final float TEXT_BOX_WIDTH = 38;
    private static final float TEXT_BOX_HEIGHT = 30;
    private static final float MIN_ROW_SIZE = 30;
    private static final float MIN_ROW_SIZE_FOR_SMALL_CHART = 1;
    private static final float MIN_LABEL_SPACING = 10;

    private static final int MAX_COL = 1000;
    private static final int MIN_DATA_STEP = 10;
    private static final float TEXT_PADDING_RIGHT = 6;
    private static final float TEXT_PADDING_BOTTOM = 6;
    private static final float LABEL_TEXT_SIZE = 10;
    private static final float CHART_PADDING_LEFT = 12;

    private static final float TOUCH_POINT_RADIUS = 4.5f;
    private static final float TOUCH_TEXT_PADDING = 4;
    private static final float TOUCH_LABEL_DELTA = 2.2f;
    private static final float TOUCH_LABEL_TOP = 13;
    private static final float HORIZON_LINE_PADDING_TOP = 3;
    private static final float HORIZON_LINE_HEIGHT = 6;

    private int mChartType = CHART_TYPE_TYPE_FULL;

    private float mTextBoxWidth = 0;
    private float mTextBoxHeight = 0;
    private float mMinRowSize;
    private float mTextPaddingRight;
    private float mTextPaddingBottom;

    private float mRowHeight = 0;
    private float mMaxValue = 0;
    private float mMinValue = 0;
    private float touchX = -1;
    private float mTouchPointRadius = 0;
    private float mLabelTouchPadding;
    private float mLabelDelta;
    private float mTouchLabelTop;
    private float mChartPaddingLeft = 0;
    private float mHorizontalLinePaddingTop;
    private float mHorizontalLinePaddingHeight;
    private float mMinLableSpacing;

    private int mRowCount = 0;
    private int mRowValueStep = 0;
    private int mChartBackgroundColor = CHART_BACKGROUND_COLOR;
    private int mChartBackgroundEndColor = CHART_BACKGROUND_COLOR;
    private int mSelectIdx = -1;

    private Paint mTextPaint;
    private Paint mVLinePaint;
    private Paint mChartLinePaint;
    private Paint mChartBackgroundPaint;
    private Paint mTouchPointPaint;
    private Paint mTouchLabelPaint;
    private Paint mTouchLinePaint;
    private Paint mHorizontalLinePaint;

    private Matrix mIMatrix = new Matrix();

    private NinePatchDrawable mPopLabel;

    private Bitmap mBackground;

    private List<ChartItemModel> mData;

    private OnSelectedListener mListener;
    private int mBottomLabelStep = 1;

    private int rowOffset = 1;

    public interface OnSelectedListener {
        void onSelectedChange(int col, ChartItemModel model);
        void onUnselected();
    }

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Paint mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setFilterBitmap(true);

        mStrokePaint.setStyle(Paint.Style.FILL);
        mStrokePaint.setColor(CHART_STROKE_COLOR);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        float density = dm.density;

        mMinRowSize = MIN_ROW_SIZE * density;

        mTextBoxWidth = TEXT_BOX_WIDTH * density;
        mTextBoxHeight = TEXT_BOX_HEIGHT * density;

        mTextPaddingRight = TEXT_PADDING_RIGHT * density;
        mTextPaddingBottom = TEXT_PADDING_BOTTOM * density;

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFilterBitmap(true);
        mTextPaint.setColor(CHART_STROKE_COLOR);
        mTextPaint.setTextSize(LABEL_TEXT_SIZE * density);

        mVLinePaint = new Paint();
        mVLinePaint.setColor(CHART_LINE_COLOR);
        mVLinePaint.setStrokeWidth(1 * density);
        mVLinePaint.setStyle(Paint.Style.STROKE);
        mVLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));

        mChartLinePaint = new Paint();
        mChartLinePaint.setColor(CHART_COLOR);
        mChartLinePaint.setStyle(Paint.Style.STROKE);
        mChartLinePaint.setAntiAlias(true);
        mChartLinePaint.setFilterBitmap(true);
        mChartLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mChartLinePaint.setStrokeJoin(Paint.Join.ROUND);
        mChartLinePaint.setStrokeWidth(1f * density);

        mTouchPointRadius = TOUCH_POINT_RADIUS * density;

        mTouchPointPaint = new Paint();
        mTouchPointPaint.setAntiAlias(true);
        mTouchPointPaint.setFilterBitmap(true);
        mTouchPointPaint.setColor(0xff333333);
        mTouchPointPaint.setStyle(Paint.Style.FILL);

        mTouchLinePaint = new Paint();
        mTouchLinePaint.setColor(0xff333333);
        mTouchLinePaint.setStyle(Paint.Style.STROKE);
        mTouchLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));
        mTouchLinePaint.setStrokeWidth(1f * density);

        mTouchLabelPaint = new Paint();
        mTouchLabelPaint.setTypeface(TypeFaceManager.getTypeFace(getContext(),
                TypeFaceManager.FONT_MYRIADPRO_SEMIBOLD));
        mTouchLabelPaint.setColor(0xffffffff);
        mTouchLabelPaint.setTextSize(12 * density);

        mHorizontalLinePaint = new Paint();
        mHorizontalLinePaint.setColor(0xffcad1d7);
        mHorizontalLinePaint.setStyle(Paint.Style.STROKE);
        mHorizontalLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));
        mHorizontalLinePaint.setStrokeWidth(1f * density);

        // Load the image as a NinePatch drawable
        mPopLabel = (NinePatchDrawable)getResources().getDrawable(R.drawable.shape_pop);
        mLabelTouchPadding = TOUCH_TEXT_PADDING * density;
        mLabelDelta = TOUCH_LABEL_DELTA * density;
        mTouchLabelTop = TOUCH_LABEL_TOP * density;

        mChartPaddingLeft = CHART_PADDING_LEFT * density;
        mHorizontalLinePaddingTop = HORIZON_LINE_PADDING_TOP * density;
        mHorizontalLinePaddingHeight = HORIZON_LINE_HEIGHT * density;

        mMinLableSpacing = MIN_LABEL_SPACING * density;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth() == 0)
            return;

        if (mRowCount == 0)
            analyzingData();

        if(mChartType == CHART_TYPE_TYPE_FULL) {

            if (mBackground == null) {
                mBackground = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas backgroundCanvas = new Canvas(mBackground);
                drawRowLabelAndLine(backgroundCanvas);
            }
            canvas.drawBitmap(mBackground, mIMatrix, null);
        }

        drawChartItemModel(canvas);
    }

    @SuppressWarnings("ConstantConditions")
    private void drawChartItemModel(Canvas canvas) {
        if(mData == null) return;

        float minValue = Float.MAX_VALUE;
        float maxValue = Float.MIN_VALUE;
        float maxCol = Math.min(MAX_COL, mData.size());
        float dataWidth = getDataWidth();
        float dataHeight = getDataHeight();
        float chartWidth = getWidth();

        float colWidth = dataWidth / (maxCol - 1);
        int colCount = (int) maxCol;

        float x0 = getX0() + mChartPaddingLeft;
        float y0 = getY0();
        float yText = getHeight() - getPaddingBottom() - mTextPaddingBottom;

        //draw horizontal line
        if(mChartType == CHART_TYPE_TYPE_SMALL) {
            Path p = new Path();
            p.moveTo(x0, y0 - dataHeight/2);
            p.lineTo(dataWidth + x0, y0 - dataHeight/2);
            canvas.drawPath(p, mHorizontalLinePaint);
        }

        Path chartPath = null;
        Path bPath = null;

        float circleX = Float.MIN_VALUE;
        float circleY = 0;
        String label = "";

        int drawLabel = 0;

        for (int i = 0; i < colCount; i++) {
            ChartItemModel item = mData.get(i);

            float x = x0 + i * colWidth;

            if(mChartType == CHART_TYPE_TYPE_FULL && drawLabel == i && x < chartWidth) {

                Rect rect = new Rect();
                mTextPaint.getTextBounds(item.dateTime, 0, item.dateTime.length(), rect);

                drawLabel += mBottomLabelStep;
                float xText = x - rect.width() / 2;
                canvas.drawLine(x, y0 + mHorizontalLinePaddingTop,
                        x, y0 + mHorizontalLinePaddingHeight + mHorizontalLinePaddingTop,
                        mVLinePaint);
                canvas.drawText(item.dateTime, xText, yText, mTextPaint);
            }

            float y = y0 - ( (item.value - mMinValue) / (mMaxValue - mMinValue)) * dataHeight;

            if(Math.abs(x - touchX) < Math.abs(circleX - touchX) ) {
                circleX = x;
                circleY = y;
                label = item.dateTime;
            }

            if(maxValue < y)
                maxValue = y;

            if(minValue > y)
                minValue = y;

            if (chartPath == null) {
                chartPath = new Path();
                chartPath.moveTo(x, y);

                bPath = new Path();
                bPath.moveTo(x0, y0);
            } else
                chartPath.lineTo(x, y);

            bPath.lineTo(x, y);
        }

        if(chartPath != null) {

            if(mChartType == CHART_TYPE_TYPE_FULL) {
                if (mChartBackgroundPaint == null) {
                    mChartBackgroundPaint = new Paint();

                    LinearGradient mVLGradient = new LinearGradient(
                            0, minValue,
                            0, maxValue,
                            mChartBackgroundColor, mChartBackgroundEndColor,
                            Shader.TileMode.CLAMP);

                    mChartBackgroundPaint.setShader(mVLGradient);
                }

                bPath.lineTo(x0 + (colCount - 1) * colWidth, y0);
                canvas.drawPath(bPath, mChartBackgroundPaint);
            }

            canvas.drawPath(chartPath, mChartLinePaint);

            if(touchX != -1) {
                drawTextLabelTouch(canvas, maxValue, circleX, label);
                canvas.drawCircle(circleX, circleY, mTouchPointRadius, mTouchPointPaint);
            }
        }
    }

    private void drawTextLabelTouch(Canvas canvas, float y0, float x, String label) {

        float textWidth = mTouchLabelPaint.measureText(label);

        float xl = x-mLabelDelta;

        // Set its bound where you need
        Rect npdBounds = new Rect((int) (xl - textWidth/2 - mLabelTouchPadding), 0,
                (int) (textWidth + mLabelTouchPadding * 2 + xl - textWidth/2), mPopLabel.getMinimumHeight());
        mPopLabel.setBounds(npdBounds);

        // Finally draw on the canvas
        mPopLabel.draw(canvas);

        Path p = new Path();
        p.moveTo(x, y0);
        p.lineTo(x, mPopLabel.getMinimumHeight());

        canvas.drawPath(p, mTouchLinePaint);

        canvas.drawText(label, (int) (xl - textWidth / 2 + mLabelTouchPadding / 2), mTouchLabelTop, mTouchLabelPaint);
    }

    private void drawRowLabelAndLine(Canvas canvas) {
        float x0 = getX0();
        float y0 = getY0();

        float rightX = x0 - mTextPaddingRight;

        Path path = new Path();

        for (int i = 0; i <= mRowCount; i++) {
            int labelValue = (int) (i * mRowValueStep + mMinValue);
            String text = getValueText(labelValue);
            //float textWidth = mTextPaint.measureText(text);
            Rect rect = new Rect();
            mTextPaint.getTextBounds(text, 0, text.length(), rect);
            float x = rightX - rect.width();
            float y = y0 - (i * mRowHeight);
            float yText = y + rect.height() / 2;

            canvas.drawText(text, x, yText, mTextPaint);
            path.moveTo(x0, y);
            path.lineTo(getWidth() - getPaddingRight(), y);
        }

        canvas.drawPath(path, mVLinePaint);
    }

    private String getValueText(int value) {
        if (value >= 1000 && value < 1000000) {
            value = value / 1000;
            return value + "K";
        } else if (value >= 1000000) {
            value = value / 1000000;
            return value + "M";
        }

        return value + "";
    }

    private float getX0() {
        return getPaddingLeft() + ( mChartType == CHART_TYPE_TYPE_FULL ? mTextBoxWidth : 0);
    }

    private float getY0() {
        return getHeight() - getPaddingBottom() - ( mChartType == CHART_TYPE_TYPE_FULL ?  mTextBoxHeight : 0 );
    }

    public void setData(List<ChartItemModel> data) {
        this.mData = data;
        if(mBackground != null && !mBackground.isRecycled())
            mBackground.recycle();
        mBackground = null;
        mRowCount = 0;
        mRowHeight = 0;
        mRowValueStep = 0;
        mMaxValue = Integer.MIN_VALUE;
        mMinValue = Integer.MAX_VALUE;
    }

    private void analyzingData() {
        if(mData == null) return;

        float maxValue = Integer.MIN_VALUE;
        float minValue = Integer.MAX_VALUE;
        String maxLabel = "";

        for (ChartItemModel item : mData) {
            if (maxValue < item.value)
                maxValue = item.value;

            if (minValue > item.value)
                minValue = item.value;

            if(maxLabel.length() < item.dateTime.length())
                maxLabel = item.dateTime;
        }

        //calculate maxValue and minValue to match with chart column
        float labelHeight = getDataHeight();
        int maxRow = (int) (labelHeight / mMinRowSize);

        int rate = MIN_DATA_STEP;
        int i = 0;
        mRowCount = maxRow + 1;

        while (mRowCount > maxRow) {

            mRowValueStep = VALUE_ROW_STEP[i] * rate;

            float halfStep = mRowValueStep/3f;

            float minMod = minValue % mRowValueStep;
            mMinValue = minValue - minMod - (( minMod > halfStep ? 0 : mRowValueStep) * rowOffset);

            if(mMinValue < 0)
                mMinValue = 0;

            float maxMod = (mRowValueStep - (maxValue % mRowValueStep)) % mRowValueStep;
            mMaxValue = maxValue + maxMod + (( maxMod > halfStep ? 0 : mRowValueStep) * rowOffset);
            mRowCount = (int) Math.ceil((mMaxValue - mMinValue) / mRowValueStep);

            i = (i + 1) % VALUE_ROW_STEP.length;
            if (i == 0)
                rate *= 10;
        }
        mRowHeight = labelHeight / mRowCount;

        //calculate bottom label step
        mBottomLabelStep = 0;
        float dataWidth = getDataWidth() - mChartPaddingLeft;
        float realWidth = dataWidth + 1;
        float sWidth = mTextPaint.measureText(maxLabel);

        while (realWidth > dataWidth) {
            mBottomLabelStep++;
            int col = mData.size() / mBottomLabelStep - 1;
            realWidth = sWidth/2 + col * sWidth + col * mMinLableSpacing;
        }
    }

    public float getDataHeight() {
        return getHeight() - getPaddingBottom() - getPaddingTop() -
                (mChartType == CHART_TYPE_TYPE_FULL ? mTextBoxHeight : 0);
    }

    public float getDataWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight() -
                (mChartType == CHART_TYPE_TYPE_FULL ? mTextBoxWidth : 0) - mChartPaddingLeft;
    }

    public void setStrokeColor(int strokeColor, int backgroundColor, int backgroundEndColor) {
        mChartLinePaint.setColor(strokeColor);
        mChartBackgroundColor = backgroundColor;//Color.argb(204, Color.red(strokeColor), Color.green(strokeColor), Color.blue(strokeColor));
        mChartBackgroundEndColor = backgroundEndColor;
    }

    @Override
    public boolean performClick() {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mData == null || mChartType != CHART_TYPE_TYPE_FULL)
            return super.onTouchEvent(event);

        int action = event.getAction();
        int idx = -1;
        if( action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE ) {
            touchX = event.getX();
            float x0 = getX0() + mChartPaddingLeft;
            float maxCol = Math.min(MAX_COL, mData.size());
            float dataWidth = getDataWidth();
            float colWidth = dataWidth / (maxCol - 1);

            float rX = touchX - x0;

            if(rX < 0)
                rX = 0;

            idx = Math.round(rX/colWidth);
        } else {
            touchX = -1;
        }

        if(idx != mSelectIdx && idx < mData.size() - 1) {
            mSelectIdx = idx;
            if(mListener != null) {
                if(idx == -1)
                    mListener.onUnselected();
                else
                    mListener.onSelectedChange(idx, mData.get(idx));
            }
            postInvalidate();
        }

        return true;
    }

    public void changeToSmallType() {
        mChartType = CHART_TYPE_TYPE_SMALL;

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        mMinRowSize = MIN_ROW_SIZE_FOR_SMALL_CHART * density;
    }

    public void setTextColor(int color){
        mTextPaint.setColor(color);
    }

    /**
     * Default of rowOffset is 1. Change it to 0 if you don't like displaying row offset.
     * @param rowOffset
     */
    public void setRowOffset(int rowOffset){
        this.rowOffset = rowOffset;
    }

    public void setSelectedListener(OnSelectedListener listener) {
        mListener = listener;
    }

}