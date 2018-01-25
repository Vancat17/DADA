package com.scujcc.dada.pay

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import kotlinx.android.synthetic.main.pay_activity.*
import kotlinx.android.synthetic.main.toolbar_header.*

class PayActivity : Activity() {

    private var contentItem: ContentItem? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_activity)
        window.statusBarColor = getColor(R.color.colorWhite)
        contentItem = intent.getSerializableExtra("JOIN") as ContentItem

        initData()
        buttonClick()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        title_text.text = "团体加入"
        detail_image.setImageResource(contentItem!!.image)
        detail_topic.text = contentItem!!.topic
        detail_price.text = "¥" + contentItem!!.price
        people_num_button.text = "1人"
        price.text = detail_price.text
    }

    @SuppressLint("SetTextI18n")
    private fun buttonClick() {
        left_button.setOnClickListener { finish() }
        info_button.setOnClickListener {  }
        people_num_button.setOnClickListener {

            val numArray = arrayOfNulls<String>(contentItem!!.totalnumber!!)

            for (i in 0..(contentItem!!.totalnumber!! - 1)) {
                numArray[i] = (i+1).toString()
            }
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(numArray, { dialog, which ->
                people_num_button.text = numArray[which] + "人"
                price.text = "¥" + (contentItem!!.price!! * (which + 1))
                dialog.dismiss()
            })
            builder.show()
        }
        confirm_button.setOnClickListener {  }
    }
}
