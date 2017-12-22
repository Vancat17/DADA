package com.scujcc.dahuo.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.scujcc.dahuo.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "设置"
        setContentView(R.layout.user_activity_setting)
    }
}
