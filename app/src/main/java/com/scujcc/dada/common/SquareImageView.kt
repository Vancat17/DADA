package com.scujcc.dada.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by  范朝波 on 2018/1/23.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class SquareImageView : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //传入参数widthMeasureSpec、heightMeasureSpec
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}