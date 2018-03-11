package com.scujcc.dada.function.collection

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.scujcc.dada.R
import com.scujcc.dada.function.stroke.OrderActivity
import kotlinx.android.synthetic.main.collection_card.view.*

/**
 * Created by  范朝波 on 2018/3/7.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class CollectionAdapter(private var mCollectionItems: MutableList<CollectionItem>) : RecyclerView.Adapter<CollectionAdapter.CollectionHolder>() {

    inner class CollectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, OrderActivity::class.java)
            v.context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.collection_card, parent, false)
        return CollectionHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        Glide.with(holder.itemView.context).load(mCollectionItems[position].avatar).into(holder.itemView.sender_image)
        Glide.with(holder.itemView.context).load(mCollectionItems[position].image).into(holder.itemView.collection_image)
        holder.itemView.price.text = mCollectionItems[position].price.toString()
        holder.itemView.sender_name.text = mCollectionItems[position].name
        holder.itemView.collection_location.text = mCollectionItems[position].location

    }

    override fun getItemCount(): Int {
        return mCollectionItems.size
    }
}