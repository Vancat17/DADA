package com.scujcc.dada.common.dateselector.adapter;

import android.support.annotation.NonNull;

import com.scujcc.dada.common.dateselector.model.DateParams;
import com.scujcc.dada.common.dateselector.model.DatePick;

import java.util.ArrayList;

/**
 * Created by  范朝波 on 2017/8/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public abstract class DatePickAdapter extends GeneralWheelAdapter {

    protected DateParams mDateParams;
    protected final DatePick mDatePick;

    public DatePickAdapter(@NonNull DateParams dateParams, @NonNull DatePick datePick) {
        mDateParams = dateParams;
        mDatePick = datePick;
        refreshValues();
    }

    public abstract int getCurrentIndex();

    public abstract void refreshValues();

    @Override
    public String getItem(int position) {
        int value = mData.get(position);
        return value < 10 ? ("0" + value) : String.valueOf(value);
    }

    public final ArrayList<Integer> getArray(int maxNum) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= maxNum; i++) {
            values.add(i);
        }
        return values;
    }
}
