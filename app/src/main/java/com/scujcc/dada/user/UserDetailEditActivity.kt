package com.scujcc.dada.user

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.user_detail_edit.*

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailEditActivity : Activity() {

    private var mUser: UserItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_edit)

        mUser = intent.getSerializableExtra("USER_DETAIL_EDIT") as UserItem

        initData()
        buttonClick()
    }

    private fun initData() {
        user_photo.setImageResource(mUser!!.photoId!!)
        detail_change_name.setText(mUser!!.name)
        detail_change_sex.text = if (mUser!!.sex == 0) "男" else "女"
        detail_change_age.text = mUser!!.age
        detail_change_job.text = mUser!!.job
        detail_change_company.setText(mUser!!.company)
        detail_change_sign.setText(mUser!!.sign)
    }

    private fun buttonClick() {
        val user = mUser
        user!!
        detail_change_cancel.setOnClickListener {
            val intent = intent
            intent.putExtra("DETAIL_RESULT",mUser)
            setResult(0,intent)
            finish()
        }
        detail_change_done.setOnClickListener {
            val intent = intent
            intent.putExtra("DETAIL_RESULT",user)
            setResult(0,intent)
            finish()
        }
        detail_change_photo.setOnClickListener { Toast.makeText(applicationContext, "换照片", Toast.LENGTH_SHORT).show() }
        detail_change_sex.setOnClickListener {
            val sexArray = arrayOf("男", "女")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                detail_change_sex.text = sexArray[which]
                dialog.dismiss()
                user.sex = if (sexArray[which] == "男") 0 else 1
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
