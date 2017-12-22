package com.scujcc.dahuo.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.scujcc.dahuo.R

class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "客服中心"
        setContentView(R.layout.user_activity_service)
    }
}
