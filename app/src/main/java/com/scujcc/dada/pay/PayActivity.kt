package com.scujcc.dada.pay

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.bumptech.glide.Glide
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.User
import kotlinx.android.synthetic.main.pay_activity.*
import kotlinx.android.synthetic.main.toolbar_header.*
import org.litepal.crud.DataSupport

class PayActivity : Activity() {

    private lateinit var content: Content

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_activity)
        window.statusBarColor = R.color.colorWhite
        content = intent.getSerializableExtra("JOIN") as Content

        initData()
        buttonClick()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        title_text.text = "团体加入"
        Glide.with(applicationContext).load(content.image).into(detail_image)
        detail_topic.text = content.topic
        detail_price.text = "¥" + content.price
        num.text = "1人"
        price.text = detail_price.text


        val user = DataSupport.findLast(User::class.java)
        name.text = user.name
        phone.text = user.phone
        location.text = user.address


    }

    @SuppressLint("SetTextI18n")
    private fun buttonClick() {
        left_button.setOnClickListener { finish() }
        rl_info.setOnClickListener {

        }
        rl_num.setOnClickListener {

            val numArray = arrayOfNulls<String>(content.total!!)

            for (i in 0..(content.total!! - 1)) {
                numArray[i] = (i+1).toString() + " 人"
            }
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(numArray, { dialog, which ->
                num.text = numArray[which]
                price.text = "¥" + (content.price!! * (which + 1))
                dialog.dismiss()
            })
            builder.show()
        }
        confirm_button.setOnClickListener {
            Toast.makeText(applicationContext,"加入成功", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
