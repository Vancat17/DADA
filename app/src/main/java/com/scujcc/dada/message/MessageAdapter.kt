package com.scujcc.dada.message

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scujcc.dada.R

/**
 * Created by  范朝波 on 2017/12/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class MessageAdapter(private val mMessageItem: List<MessageItem>): RecyclerView.Adapter<MessageAdapter.IndexHolder>()  {

    inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View) {
            val intent = Intent(v.context, ChatActivity::class.java)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mMessageItem.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IndexHolder {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.message_card, parent, false)
        return IndexHolder(view)
    }

    override fun onBindViewHolder(holder: IndexHolder?, position: Int) {

    }
}