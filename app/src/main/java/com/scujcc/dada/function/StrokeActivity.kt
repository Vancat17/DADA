package com.scujcc.dada.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.scujcc.dada.R

class StrokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的行程"
        setContentView(R.layout.user_activity_stroke)
    }
}
