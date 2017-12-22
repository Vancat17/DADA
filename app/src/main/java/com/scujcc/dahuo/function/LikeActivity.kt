package com.scujcc.dahuo.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.scujcc.dahuo.R

class LikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的收藏"
        setContentView(R.layout.user_activity_like)
    }
}
