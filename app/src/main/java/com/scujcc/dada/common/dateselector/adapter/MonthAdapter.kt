package com.scujcc.dada.common.dateselector.adapter

import com.scujcc.dada.common.dateselector.model.DateParams
import com.scujcc.dada.common.dateselector.model.DatePick


/**
 * Created by  范朝波 on 2017/8/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class MonthAdapter(dateParams: DateParams, datePick: DatePick) : com.scujcc.dada.common.dateselector.adapter.DatePickAdapter(dateParams, datePick) {

    override fun getItem(position: Int): String {
        val number = mData[position]
        val value = if (number < 10) "0" + number else "" + number
        return value + "月"
    }
    override fun getCurrentIndex(): Int {
        return mData.indexOf(mDatePick.month)
    }

    override fun refreshValues() {
        setData(getArray(12))
    }
}
