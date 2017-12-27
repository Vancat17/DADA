package com.scujcc.dada.content

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.toolbar_header.*

class ContentDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)

        val contentItem = intent.getSerializableExtra("SER") as ContentItem

        detail_recycler.setHasFixedSize(true)
        detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        detail_recycler.adapter = ContentDetailAdapter(contentItem)

        buttonClick()
    }


    private fun buttonClick() {
        left_button.setOnClickListener { finish() }
        right_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { Toast.makeText(applicationContext, "收藏", Toast.LENGTH_SHORT).show() }
        talk_button.setOnClickListener { Toast.makeText(applicationContext, "交流", Toast.LENGTH_SHORT).show() }
        join_button.setOnClickListener { Toast.makeText(applicationContext, "加入", Toast.LENGTH_SHORT).show() }
    }
}
