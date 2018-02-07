package com.scujcc.dada.common.dateselector.adapter

import com.scujcc.dada.common.dateselector.model.DateParams
import com.scujcc.dada.common.dateselector.model.DatePick


/**
 * Created by  范朝波 on 2017/8/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class HourAdapter(dateParams: DateParams, datePick: DatePick) : com.scujcc.dada.common.dateselector.adapter.DatePickAdapter(dateParams, datePick) {

    override fun getCurrentIndex(): Int {
        return mData.indexOf(mDatePick.hour)
    }

    override fun refreshValues() {
        setData(getArray(24))
    }
}
