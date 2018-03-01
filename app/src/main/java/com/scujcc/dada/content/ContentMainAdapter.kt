package com.scujcc.dada.content

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.scujcc.dada.R
import kotlinx.android.synthetic.main.content_main_card.view.*


/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentMainAdapter(private val mContentItems: List<ContentItem>) : RecyclerView.Adapter<ContentMainAdapter.ContentHolder>() {

    inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            val intent = Intent(v.context, ContentDetailActivity::class.java)
            intent.putExtra("SER", mContentItems[adapterPosition])
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_main_card, parent, false)
        return ContentHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        when (getItemViewType(position)) {
            BANNER_TYPE -> {
            }
            NULL_TYPE -> {
            }
            else -> {
                val pos = if (position - ITEM_EXTRA > 0) position - ITEM_EXTRA else 0
//                val uri = "http://pic2.52pk.com/files/170511/2165322_100509_1_lit.jpg"
                val uri = "https://dada-1256129579.cos.ap-chengdu.myqcloud.com/1024*1024.png"
                Glide.with(holder.itemView.context).load(uri).into(holder.itemView.activity_image)
                holder.itemView.activity_sender.text = mContentItems[pos].sender
                holder.itemView.activity_topic.text = mContentItems[pos].topic
                holder.itemView.activity_tag.text = mContentItems[pos].tag
                holder.itemView.activity_time.text = mContentItems[pos].time
                holder.itemView.activity_location.text = mContentItems[pos].location
                holder.itemView.activity_num.text = (mContentItems[pos].totalnumber!! - 1).toString() + "/" + mContentItems[pos].totalnumber.toString()
                if (mContentItems[pos].price == 0.00) {
                    holder.itemView.activity_price.text = "免费"
                } else {
                    holder.itemView.activity_price.text = "¥" + mContentItems[pos].price.toString()
                }
                holder.itemView.activity_content.text = mContentItems[pos].content
            }
        }
    }

    override fun getItemCount(): Int {

        return if (mContentItems.size < 20) mContentItems.size else 20
    }

    override fun getItemViewType(position: Int): Int {

        return DEFAULT_TYPE
    }

    companion object {

        private val BANNER_TYPE = 1
        private val NULL_TYPE = 2
        private val DEFAULT_TYPE = 3
        private val ITEM_EXTRA = 0
    }
}
