package com.scujcc.dada.message

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import kotlinx.android.synthetic.main.chat_activity.*

class ChatActivity : Activity() {

    private var contentItem: ContentItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        contentItem = intent.getSerializableExtra("CHAT") as ContentItem

        initData()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        title = contentItem!!.sender
        detail_image.setImageResource(contentItem!!.imageId)
        detail_topic.text = contentItem!!.topic
        detail_price.text = "¥" + contentItem!!.price
    }
}
