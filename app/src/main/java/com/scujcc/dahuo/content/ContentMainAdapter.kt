package com.scujcc.dahuo.content

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scujcc.dahuo.R
import kotlinx.android.synthetic.main.content_main_card.view.*


/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentMainAdapter(private val mContentItems: List<ContentItem>) : RecyclerView.Adapter<ContentMainAdapter.IndexHolder>() {

    open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class BannerHolder(itemView: View) : IndexHolder(itemView)

    inner class ContentHolder(itemView: View) : IndexHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, ContentDetailActivity::class.java)
            v.context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexHolder {

        val holder: IndexHolder
        val view: View
        when (viewType) {

            BANNER_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.content_main_card, parent, false)
                holder = ContentHolder(view)
            }
            NULL_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.content_main_card, parent, false)
                holder = ContentHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.content_main_card, parent, false)
                holder = ContentHolder(view)
            }
        }
        return holder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IndexHolder, position: Int) {
        when (getItemViewType(position)) {
            BANNER_TYPE -> {
            }
            NULL_TYPE -> {
            }
            else -> {
                val contentHolder = holder as ContentHolder
                val pos = if (position - ITEM_EXTRA > 0) position - ITEM_EXTRA else 0
                contentHolder.itemView.activity_image.setImageResource(mContentItems[pos].imageId)
                contentHolder.itemView.activity_sender.text = mContentItems[pos].sender
                contentHolder.itemView.activity_topic.text = mContentItems[pos].topic
                contentHolder.itemView.activity_tag.text = mContentItems[pos].tag
                contentHolder.itemView.activity_time.text = mContentItems[pos].time
                contentHolder.itemView.activity_location.text = mContentItems[pos].locaticon
                contentHolder.itemView.activity_num.text = (mContentItems[pos].num - 1).toString() + "/" + mContentItems[pos].num.toString()
                if (mContentItems[pos].price == 0) {
                    contentHolder.itemView.activity_price.text = "免费"
                } else {
                    contentHolder.itemView.activity_price.text = "¥" + mContentItems[pos].price.toString()
                }
                contentHolder.itemView.activity_content.text = mContentItems[pos].content
            }
        }
    }

    override fun getItemCount(): Int {
        return mContentItems.size
    }

    override fun getItemViewType(position: Int): Int {
        //        switch (position) {
        //            case 0:
        //                return BANNER_TYPE;
        //            case 1:
        //                return NULL_TYPE;
        //            default:
        //                return DEFAULT_TYPE;
        //        }

        return DEFAULT_TYPE
    }

    companion object {

        private val BANNER_TYPE = 1
        private val NULL_TYPE = 2
        private val DEFAULT_TYPE = 3
        private val ITEM_EXTRA = 0
    }
}
