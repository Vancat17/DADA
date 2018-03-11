package com.scujcc.dada.content

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.scujcc.dada.R
import com.scujcc.dada.helper.Content
import kotlinx.android.synthetic.main.content_detail_header.view.*

/**
 * Created by  范朝波 on 2017/12/24.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class ContentDetailAdapter(private val item: Content): RecyclerView.Adapter<ContentDetailAdapter.IndexHolder>() {

    open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class DetailHolder(itemView: View) : IndexHolder(itemView)

    inner class RecommendHolder(itemView: View) : IndexHolder(itemView)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IndexHolder, position: Int) {

        when (position) {
            0 -> {
                val detailHolder = holder as DetailHolder
                Glide.with(holder.itemView.context).load(item.image).into(holder.itemView.detail_image)
                Glide.with(holder.itemView.context).load(item.avatar).into(holder.itemView.sender_image)
                detailHolder.itemView.detail_topic.text = item.topic
                detailHolder.itemView.sender_name.text = item.name
                detailHolder.itemView.detail_location.text = item.location
                detailHolder.itemView.detail_time.text = item.date
                detailHolder.itemView.detail_price.text = "¥" + item.price
                detailHolder.itemView.detail_people_num.text = "当前人数" + item.now + "/" + item.total
                detailHolder.itemView.activity_tag.text = item.tag
                detailHolder.itemView.detail_detail.text = item.content
                detailHolder.itemView.sender_names.text = item.name
                detailHolder.itemView.sender_score.text = item.score.toString()
            }
            else -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IndexHolder {

        val holder: IndexHolder
        val view: View
        when (viewType) {

            DETAIL_TYPE -> {
                view = LayoutInflater.from(parent!!.context).inflate(R.layout.content_detail_header, parent, false)
                holder = DetailHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent!!.context).inflate(R.layout.recommend_card, parent, false)
                holder = RecommendHolder(view)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> DETAIL_TYPE
            else -> RECOMMEND_TYPE
        }
    }

    companion object {

        private val DETAIL_TYPE = 1
        private val RECOMMEND_TYPE = 2
    }
}