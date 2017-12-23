package com.scujcc.dahuo.content

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.scujcc.dahuo.R
import com.scujcc.dahuo.TestActivity

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView

/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentMainAdapter(private val mContentItems: List<ContentItem>) : RecyclerView.Adapter<ContentMainAdapter.IndexHolder>() {

    open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class BannerHolder(itemView: View) : IndexHolder(itemView)

    inner class ContentHolder(itemView: View) : IndexHolder(itemView), View.OnClickListener {

        internal var mUserPhoto: ShapedImageView
        internal var mUserName: TextView
        internal var mUserVip: ImageView
        internal var mStartTime: TextView
        internal var mContentTopic: TextView
        internal var mContentText: TextView
        internal var mContentImage: ImageView
        internal var mContentTolNum: TextView

        init {
            itemView.setOnClickListener(this)

            mUserPhoto = itemView.findViewById(R.id.user_photo)
            mUserName = itemView.findViewById(R.id.user_name)
            mUserVip = itemView.findViewById(R.id.user_vip)
            mStartTime = itemView.findViewById(R.id.start_time)
            mContentTopic = itemView.findViewById(R.id.content_topic)
            mContentText = itemView.findViewById(R.id.content_text)
            mContentTolNum = itemView.findViewById(R.id.content_tolNum)
            mContentImage = itemView.findViewById(R.id.content_image)
        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, TestActivity::class.java)
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
                contentHolder.mUserPhoto.setImageResource(mContentItems[pos].photoId)
                contentHolder.mUserName.text = mContentItems[pos].name
                //          p7      contentHolder.mUserVip.
                contentHolder.mStartTime.text = mContentItems[pos].startTime
                contentHolder.mContentTopic.text = mContentItems[pos].topic
                contentHolder.mContentText.text = mContentItems[pos].content
                //                contentHolder.mContentImage.setImageResource(mContentItems.get(pos).get);
                contentHolder.mContentTolNum.text = mContentItems[pos].nowNum.toString() + "/" + mContentItems[position].nowNum
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
