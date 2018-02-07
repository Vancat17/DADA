package com.scujcc.dada.common.dateselector.adapter

import com.scujcc.dada.common.dateselector.model.DateParams
import com.scujcc.dada.common.dateselector.model.DatePick

import java.util.Calendar

/**
 * Created by  范朝波 on 2017/8/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class DayAdapter(dateParams: DateParams, datePick: DatePick) : com.scujcc.dada.common.dateselector.adapter.DatePickAdapter(dateParams, datePick) {

    override fun getCurrentIndex(): Int {
        return mData.indexOf(mDatePick.day)
    }

    override fun refreshValues() {
        val calendar = Calendar.getInstance()
        calendar.set(mDatePick.year, mDatePick.month - 1, 1)

        val day = mDatePick.day
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        mDatePick.day = Math.min(day, maxDay)

        setData(getArray(maxDay))
    }
}
