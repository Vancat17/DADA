package com.scujcc.dahuo.user

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.scujcc.dahuo.R

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView

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

    @OnClick(R.id.change_cancel, R.id.change_done, R.id.change_sex, R.id.change_age, R.id.change_job, R.id.change_company, R.id.change_sign)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.change_cancel -> finish()
            R.id.change_done -> finish()
            R.id.change_sex -> {
            }
            R.id.change_age -> {
            }
            R.id.change_job -> {
            }
            R.id.change_company -> {
            }
            R.id.change_sign -> {
            }
        }
    }
}
