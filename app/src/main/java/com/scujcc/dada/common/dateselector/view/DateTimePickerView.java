package com.scujcc.dada.common.dateselector.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scujcc.dada.common.dateselector.OnChangeListener;
import com.scujcc.dada.common.dateselector.adapter.DatePickAdapter;
import com.scujcc.dada.common.dateselector.adapter.DayAdapter;
import com.scujcc.dada.common.dateselector.adapter.HourAdapter;
import com.scujcc.dada.common.dateselector.adapter.MinuteAdapter;
import com.scujcc.dada.common.dateselector.adapter.MonthAdapter;
import com.scujcc.dada.common.dateselector.adapter.YearAdapter;
import com.scujcc.dada.common.dateselector.model.DateParams;
import com.scujcc.dada.common.dateselector.model.DatePick;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by  范朝波 on 2017/8/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
public class DateTimePickerView extends LinearLayout {

    final DatePick mDatePick = new DatePick();
    private OnChangeListener mOnChangeListener;
    private WheelView mDayView;
    private DatePickAdapter mDayAdapter;

    public DateTimePickerView(Context context) {
        super(context);
    }

    public DateTimePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DateTimePickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public void show(@NonNull DateParams params) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(params.currentDate);
        mDatePick.setData(calendar);

        if(params.types == null) {
            return;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        for(int type : params.types) {

            WheelView wheelView = new WheelView(getContext());
            final DatePickAdapter datePickAdapter;

            switch (type) {
                case DateParams.TYPE_YEAR:
                    wheelView.setCyclic(false);
                    wheelView.setAdapter(datePickAdapter = new YearAdapter(params, mDatePick));
                    wheelView.setCurrentItem(datePickAdapter.getCurrentIndex());
                    layoutParams.weight = 3;
                    wheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(@NonNull WheelView wheel, int oldValue, int newValue) {
                            mDatePick.year = datePickAdapter.getValue(newValue);
                            mDayAdapter.refreshValues();
                            mDayView.setCurrentItem(mDayAdapter.getCurrentIndex());
                            DateTimePickerView.this.onChanged();
                        }
                    });
                    break;

                case DateParams.TYPE_MONTH:
                    wheelView.setCyclic(true);
                    wheelView.setAdapter(datePickAdapter = new MonthAdapter(params, mDatePick));
                    wheelView.setCurrentItem(datePickAdapter.getCurrentIndex());
                    layoutParams.weight = 2;
                    wheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(@NonNull WheelView wheel, int oldValue, int newValue) {
                            mDatePick.month = datePickAdapter.getValue(newValue);
                            mDayAdapter.refreshValues();
                            mDayView.setCurrentItem(mDayAdapter.getCurrentIndex());
                            DateTimePickerView.this.onChanged();
                        }
                    });
                    break;

                case DateParams.TYPE_DAY:
                    mDayView = wheelView;
                    datePickAdapter = new DayAdapter(params, mDatePick);
                    mDayAdapter = datePickAdapter;

                    wheelView.setCyclic(true);
                    wheelView.setAdapter(datePickAdapter);
                    wheelView.setCurrentItem(datePickAdapter.getCurrentIndex());
                    layoutParams.weight = 2;
                    wheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(@NonNull WheelView wheel, int oldValue, int newValue) {
                            mDatePick.day = datePickAdapter.getValue(newValue);
                            DateTimePickerView.this.onChanged();
                        }
                    });
                    break;

                case DateParams.TYPE_HOUR:
                    wheelView.setCyclic(true);
                    wheelView.setAdapter(datePickAdapter = new HourAdapter(params, mDatePick));
                    wheelView.setCurrentItem(datePickAdapter.getCurrentIndex());
                    wheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(@NonNull WheelView wheel, int oldValue, int newValue) {
                            mDatePick.hour = datePickAdapter.getValue(newValue);
                            DateTimePickerView.this.onChanged();
                        }
                    });
                    layoutParams.weight = 2;
                    break;

                case DateParams.TYPE_MINUTE:
                    wheelView.setCyclic(true);
                    wheelView.setAdapter(datePickAdapter = new MinuteAdapter(params, mDatePick));
                    wheelView.setCurrentItem(datePickAdapter.getCurrentIndex());
                    wheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(@NonNull WheelView wheel, int oldValue, int newValue) {
                            mDatePick.minute = datePickAdapter.getValue(newValue);
                            DateTimePickerView.this.onChanged();
                        }
                    });
                    layoutParams.weight = 2;
                    break;
            }

            addView(wheelView, layoutParams);

            if(type == DateParams.TYPE_HOUR) {
                layoutParams.weight = 0;
                TextView textView = new TextView(getContext());
                textView.setGravity(Gravity.CENTER);
                TextPaint paint = textView.getPaint();
                paint.setFakeBoldText(true);
                textView.setText(":");
                textView.setTextColor(0xff444444);
                addView(textView, layoutParams);
            }
        }
    }

    private void onChanged() {
        if(mOnChangeListener != null) {
            mOnChangeListener.onChanged(getSelectDate());
        }
    }

    public Date getSelectDate() {
        int year = mDatePick.year;
        int moth = mDatePick.month;
        int day = mDatePick.day;
        int hour = mDatePick.hour;
        int minute = mDatePick.minute;

        Calendar calendar = Calendar.getInstance();

        calendar.set(year, moth - 1, day, hour, minute);
        return calendar.getTime();
    }
}
