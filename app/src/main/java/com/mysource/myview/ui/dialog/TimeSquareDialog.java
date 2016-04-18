package com.mysource.myview.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.marcohc.robotocalendar.RobotoCalendarView;
import com.mysource.myview.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

public class TimeSquareDialog extends BaseDialog {

    @Bind(R.id.robotoCalendarPicker)
    RobotoCalendarView robotoCalendarView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    Date currentDate, startDate, endDate;
    TimeSquareDialogListener mTimeSquareDialogListener;
    /**
     * modeSelect: 0 -> normal, 1-> start date, 2-> end date
     */
    int modeSelect;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    public TimeSquareDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_timesquare);
        if (startDate != null && endDate != null) {
            tvTitle.setText(getContext().getString(R.string.dialog_timesquare_to_date));
        } else {
            tvTitle.setText(getContext().getString(R.string.dialog_timesquare_from_date));
        }
        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(new RobotoCalendarView.RobotoCalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                tvTitle.setText(getContext().getString(R.string.dialog_timesquare_to_date));
                if (mTimeSquareDialogListener != null) {
                    if (robotoCalendarView.getStartDay() == null || modeSelect == RobotoCalendarView.MODE_START_DATE) {
                        mTimeSquareDialogListener.onSelectStartDate(date);
                    } else {
                        if (robotoCalendarView.getEndDay() == null || modeSelect == RobotoCalendarView.MODE_END_DATE) {
                            mTimeSquareDialogListener.onSelectEndDate(date);
                        }
                    }
                }
                // Mark calendar day
                boolean isSelect = robotoCalendarView.selectDay(date, modeSelect);
                if (getModeSelect() != RobotoCalendarView.MODE_NORMAL) {
                    setModeSelect(RobotoCalendarView.MODE_NORMAL);
                }
                if (isSelect) {
                    if (robotoCalendarView.getStartDay() != null && robotoCalendarView.getEndDay() != null && modeSelect == RobotoCalendarView.MODE_NORMAL) {
                        robotoCalendarView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mTimeSquareDialogListener != null)
                                    mTimeSquareDialogListener.onFinishSelect(robotoCalendarView);
                                dismiss();
                            }
                        }, 1000);
                    }
                }

            }

            @Override
            public void onRightButtonClick() {
                currentMonthIndex++;
                updateCalendar();
            }

            @Override
            public void onLeftButtonClick() {
                currentMonthIndex--;
                updateCalendar();
            }
        });

        // Initialize the RobotoCalendarPicker with the current index and date
        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        if (currentDate != null) currentCalendar.setTime(currentDate);
        robotoCalendarView.setStartDay(startDate);
        robotoCalendarView.setEndDay(endDate);
        // Mark current day
        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());
        robotoCalendarView.updateBackgroundSelectDays();
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
    }

    public boolean isReset() {
        return startDate == null && endDate == null;
    }

    @OnClick(R.id.bt_right)
    public void onRightClick(View view) {
        this.dismiss();
    }

    @OnClick(R.id.bt_left)
    public void onLeftClick(View view) {
        tvTitle.setText(getContext().getString(R.string.dialog_timesquare_from_date));
        robotoCalendarView.reset();
        if (mTimeSquareDialogListener != null) mTimeSquareDialogListener.onReset();
    }

    public void show(Date currentDate, Date startDate, Date endDate, TimeSquareDialogListener listener) {
        this.currentDate = currentDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mTimeSquareDialogListener = listener;
        super.show();
    }

    public Date getEndDate() {
        return robotoCalendarView.getEndDay();
    }

    public Date getStartDate() {
        return robotoCalendarView.getStartDay();
    }

    public Date getCurrentDate() {
        return robotoCalendarView.getCurrentDay();
    }

    public int getModeSelect() {
        return modeSelect;
    }

    /**
     * @param modeSelect
     */
    public void setModeSelect(int modeSelect) {
        this.modeSelect = modeSelect;
    }

    @Override
    public void dismiss() {
        mTimeSquareDialogListener.onDismiss(robotoCalendarView);
        super.dismiss();
    }

    public interface TimeSquareDialogListener {
        void onSelectStartDate(Date date);

        void onSelectEndDate(Date date);

        void onReset();

        void onFinishSelect(RobotoCalendarView robotoCalendarView);

        void onDismiss(RobotoCalendarView robotoCalendarView);
    }


}
