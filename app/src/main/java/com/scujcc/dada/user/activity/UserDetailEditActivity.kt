package com.scujcc.dada.user.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.bumptech.glide.Glide
import com.scujcc.dada.R
import com.scujcc.dada.helper.User
import kotlinx.android.synthetic.main.user_detail_edit.*
import org.litepal.crud.DataSupport

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailEditActivity : Activity() {

    private var user = DataSupport.findFirst(User::class.java)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_edit)

        initData()
        buttonClick()
    }

    private fun initData() {
        Glide.with(applicationContext).load(user.avatar).into(user_photo)
//        user_photo.setImageResource(R.drawable.dada)
        detail_change_name.setText(user.name)
        detail_change_sex.text = if (user.sex == "男") "男" else "女"
        detail_change_age.text = user.age
        detail_change_job.text = user.job
        detail_change_company.setText(user.company)
        detail_change_sign.setText(user.sign)
    }

    private fun buttonClick() {

        detail_change_cancel.setOnClickListener {
            finish()
        }
        detail_change_done.setOnClickListener {

            /**
             * 文本变化
             */
            user.name = detail_change_name.text.toString()
            user.company = detail_change_company.text.toString()
            user.sign = detail_change_sign.text.toString()

            //更新用户
            user.save()
            //向服务器提交用户信息

            finish()
        }
        detail_change_photo.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
        detail_change_sex.setOnClickListener {
            val sexArray = arrayOf("男", "女")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                detail_change_sex.text = sexArray[which]
                dialog.dismiss()
                user.sex = if (sexArray[which] == "男") "男" else "女"
            })
            builder.show()
        }

        detail_change_age.setOnClickListener {
            val sexArray = arrayOf("1950后","1960后","1970后","1980后","1990后","2000后","2010后")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                detail_change_age.text = sexArray[which]
                dialog.dismiss()
                user.age = sexArray[which]
            })
            builder.show()
        }
        detail_change_job.setOnClickListener {
            val sexArray = arrayOf("健康医疗","旅游","金融","信息安全","教育","IT互联网","销售行业","媒体娱乐", "机械")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                detail_change_job.text = sexArray[which]
                dialog.dismiss()
                user.job = sexArray[which]
            })
            builder.show()
        }
    }
}
