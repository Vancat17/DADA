package com.scujcc.dada.message

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import kotlinx.android.synthetic.main.chat_activity.*
import kotlinx.android.synthetic.main.toolbar_header.*

class ChatActivity : Activity() {

    private var contentItem: ContentItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        window.statusBarColor = Color.WHITE
        contentItem = intent.getSerializableExtra("CHAT") as ContentItem

        initData()

        saveChatHistory()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        title_text.text = contentItem!!.sender
        detail_image.setImageResource(contentItem!!.imageId)
        detail_topic.text = contentItem!!.topic
        detail_price.text = "¥" + contentItem!!.price
    }

    //聊天信息存入本地
    //1.存联系人
    //2.存信息
    private fun saveChatHistory() {
        MessageItem("11368368",R.drawable.ic_default_image,contentItem!!.sender, "明天确定要去吗？", contentItem!!.imageId, contentItem!!.time).save()
    }
}
