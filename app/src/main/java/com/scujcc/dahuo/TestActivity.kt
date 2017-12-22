package com.scujcc.dahuo

import android.app.Activity
import android.content.Context
import android.content.Intent
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
        backButton.setOnClickListener {
            finish()
        }
    }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, TestActivity::class.java)
        }
    }
}
