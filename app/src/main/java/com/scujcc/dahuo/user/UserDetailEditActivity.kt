package com.scujcc.dahuo.user

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.scujcc.dahuo.R

import butterknife.OnClick

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailEditActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_edit)
    }

    @OnClick(R.id.detail_change_cancel, R.id.detail_change_done, R.id.detail_change_sex, R.id.detail_change_age, R.id.detail_change_job, R.id.detail_change_company, R.id.detail_change_sign)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.detail_change_cancel -> finish()
            R.id.detail_change_done -> finish()
            R.id.detail_change_sex -> {
            }
            R.id.detail_change_age -> {
            }
            R.id.detail_change_job -> {
            }
            R.id.detail_change_company -> {
            }
            R.id.detail_change_sign -> {
            }
        }
    }
}
