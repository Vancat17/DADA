package com.scujcc.dada.message

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.scujcc.dada.R
import kotlinx.android.synthetic.main.message_activity.*

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class MessageActivity : AppCompatActivity() {

    private  var mMessageItems: MutableList<MessageItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "消息"
        setContentView(R.layout.message_activity)

        mMessageItems = ArrayList()
        message_recycler.setHasFixedSize(true)
        message_recycler.layoutManager = LinearLayoutManager(applicationContext)
        message_recycler.adapter = MessageAdapter(mMessageItems!!)
    }
}
