package com.scujcc.dada.function.stroke

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.stroke_card.view.*

/**
 * Created by  范朝波 on 2017/12/29.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class StrokeAdapter(private var mStrokeItems: MutableList<StrokeItem>) : RecyclerView.Adapter<StrokeAdapter.StrokeHolder>() {

    inner class StrokeHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, OrderActivity::class.java)
            v.context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrokeHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stroke_card, parent, false)
        return StrokeHolder(view)
    }

    override fun onBindViewHolder(holder: StrokeHolder, position: Int) {
        Glide.with(holder.itemView.context).load(mStrokeItems[position].image).into(holder.itemView.stroke_image)
        holder.itemView.stroke_price.text = mStrokeItems[position].price.toString()
        holder.itemView.stroke_topic.text = mStrokeItems[position].topic
        holder.itemView.stroke_state.text = mStrokeItems[position].state
    }

    override fun getItemCount(): Int {
        return mStrokeItems.size
    }
}