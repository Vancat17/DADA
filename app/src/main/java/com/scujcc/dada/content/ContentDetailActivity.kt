package com.scujcc.dada.content

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.content_detail.*

class ContentDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)

        val contentItem = intent.getSerializableExtra("SER") as ContentItem

        content_detail_recycler.setHasFixedSize(true)
        content_detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        content_detail_recycler.adapter = ContentDetailAdapter(contentItem)

        buttonClick()

        content_detail_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                detail_toolbar.setBackgroundColor(Color.WHITE)
            }
        })
    }

    private fun buttonClick() {
        back_button.setOnClickListener { finish() }
        share_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { Toast.makeText(applicationContext, "收藏", Toast.LENGTH_SHORT).show() }
        talk_button.setOnClickListener { Toast.makeText(applicationContext, "交流", Toast.LENGTH_SHORT).show() }
        join_button.setOnClickListener { Toast.makeText(applicationContext, "加入", Toast.LENGTH_SHORT).show() }
    }
}
