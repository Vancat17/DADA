package com.scujcc.dahuo

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.test.*

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        back_button.setOnClickListener {
            finish()
        }
    }
}
