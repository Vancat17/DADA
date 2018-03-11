package com.scujcc.dada.message

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

import com.scujcc.dada.R
import kotlinx.android.synthetic.main.message_activity.*
import org.litepal.crud.DataSupport

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class MessageActivity : AppCompatActivity() {

    private lateinit var mMessageItems: MutableList<MessageItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_activity)
        title = "消息"

        mMessageItems = DataSupport.findAll(MessageItem::class.java)

        message_recycler.setHasFixedSize(true)
        message_recycler.layoutManager = LinearLayoutManager(applicationContext)
        message_recycler.adapter = MessageAdapter(mMessageItems)

        if (mMessageItems.isNotEmpty()) {
            no_message.visibility = View.GONE
        }
    }
}
