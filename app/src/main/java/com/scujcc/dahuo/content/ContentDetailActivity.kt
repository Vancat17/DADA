package com.scujcc.dahuo.content

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.scujcc.dahuo.R
import kotlinx.android.synthetic.main.content_detail.*

class ContentDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)

        content_detail_recycler.setHasFixedSize(true)
        content_detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        content_detail_recycler.adapter = ContentDetailAdapter()

        buttonClick()
    }

    private fun buttonClick() {
        back_button.setOnClickListener { finish() }
        share_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { Toast.makeText(applicationContext, "收藏", Toast.LENGTH_SHORT).show() }
        talk_button.setOnClickListener { Toast.makeText(applicationContext, "交流", Toast.LENGTH_SHORT).show() }
        join_button.setOnClickListener { Toast.makeText(applicationContext, "加入", Toast.LENGTH_SHORT).show() }
    }
}
