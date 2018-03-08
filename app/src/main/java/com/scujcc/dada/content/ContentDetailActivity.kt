package com.scujcc.dada.content

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.avos.avoscloud.im.v2.AVIMClient
import com.avos.avoscloud.im.v2.AVIMException
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback
import com.scujcc.dada.R
import com.scujcc.dada.chatkit.LCChatKit
import com.scujcc.dada.chatkit.activity.LCIMConversationActivity
import com.scujcc.dada.chatkit.utils.LCIMConstants
import com.scujcc.dada.helper.User
import com.scujcc.dada.pay.PayActivity
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.toolbar_header.*
import org.litepal.crud.DataSupport

class ContentDetailActivity : Activity() {

    private lateinit var contentItem: ContentItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)
        window.statusBarColor = Color.TRANSPARENT
        contentItem = intent.getSerializableExtra("SER") as ContentItem

        detail_recycler.setHasFixedSize(true)
        detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        detail_recycler.adapter = ContentDetailAdapter(contentItem)

        buttonClick()
    }

    private fun buttonClick() {
        val user = DataSupport.findLast(User::class.java)
        left_button.setOnClickListener { finish() }
        right_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { }
        talk_button.setOnClickListener {

            //开启聊天
            LCChatKit.getInstance().open(user.name, object : AVIMClientCallback() {
                override fun done(p0: AVIMClient?, p1: AVIMException?) {

                    if (null == p1) {
                        val intent = Intent(applicationContext, LCIMConversationActivity::class.java).apply {
                            putExtra(LCIMConstants.PEER_ID, contentItem.sender)
                        }
                        startActivity(intent)
                    }
                }
            })
        }
        join_button.setOnClickListener {
            val intent = Intent(applicationContext, PayActivity::class.java)
            intent.putExtra("JOIN", contentItem)
            startActivity(intent)
        }
    }
}

