package com.scujcc.dada.user

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.user_detail_edit.*


/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailEditActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_edit)

        buttonClick()
    }

    private fun buttonClick() {
        detail_change_cancel.setOnClickListener { finish() }
        detail_change_done.setOnClickListener { finish() }
        detail_change_photo.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
        detail_change_sex.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
        detail_change_age.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
        detail_change_job.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
    }
}
