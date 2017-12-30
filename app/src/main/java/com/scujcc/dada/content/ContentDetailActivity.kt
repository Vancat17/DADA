package com.scujcc.dada.content

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.message.ChatActivity
import com.scujcc.dada.pay.PayActivity
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.toolbar_header.*

class ContentDetailActivity : Activity() {

    private var contentItem: ContentItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)

        contentItem = intent.getSerializableExtra("SER") as ContentItem

        detail_recycler.setHasFixedSize(true)
        detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        detail_recycler.adapter = ContentDetailAdapter(contentItem!!)

        isLiekd()
        buttonClick()
    }

    private fun buttonClick() {
        left_button.setOnClickListener { finish() }
        right_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { isLiekd() }
        talk_button.setOnClickListener {
            val intent = Intent(applicationContext, ChatActivity::class.java)
            startActivity(intent)
        }
        join_button.setOnClickListener {
            val intent = Intent(applicationContext, PayActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isLiekd() {
        if (contentItem!!.isLiked) {
            like_button_text.text = "已收藏"
            like_button.setImageDrawable(getDrawable(R.drawable.ic_liked))
            Toast.makeText(applicationContext, "已收藏", Toast.LENGTH_SHORT).show()
            contentItem!!.isLiked = false
        } else {
            like_button_text.text = "收藏"
            like_button.setImageDrawable(getDrawable(R.drawable.ic_like))
            contentItem!!.isLiked = true
        }
    }
}

