package com.scujcc.dada.content

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.content_detail_header.view.*

/**
 * Created by  范朝波 on 2017/12/24.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class ContentDetailAdapter(private val item: ContentItem): RecyclerView.Adapter<ContentDetailAdapter.IndexHolder>() {

    open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HeaderHolder(itemView: View) : IndexHolder(itemView)

    inner class IntroductionHolder(itemView: View) : IndexHolder(itemView)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IndexHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER_TYPE -> {
                var headerHolder = holder as HeaderHolder
                headerHolder.itemView.detail_image.setImageResource(item.imageId)
                headerHolder.itemView.sender_name.text = "搭搭@" + item.sender
                headerHolder.itemView.detail_topic.text = item.topic
                headerHolder.itemView.detail_price.text = "¥" + item.price
                headerHolder.itemView.detail_people_num.text = "人数: " + (item.num - 1) + "/" + item.num
                headerHolder.itemView.activity_tag.text = item.tag

            }
            else -> {
            }
        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IndexHolder {
        val holder: IndexHolder
        val view: View
        when (viewType) {

            HEADER_TYPE -> {
                view = LayoutInflater.from(parent!!.context).inflate(R.layout.content_detail_header, parent, false)
                holder = HeaderHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent!!.context).inflate(R.layout.content_detail_content, parent, false)
                holder = IntroductionHolder(view)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> DEFAULT_TYPE
        }
//        return DEFAULT_TYPE
    }

    companion object {

        private val HEADER_TYPE = 1
        private val DEFAULT_TYPE = 2
        private val ITEM_EXTRA = 0
    }
}