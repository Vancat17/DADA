package com.scujcc.dada.common.dateselector.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.add_category_item.view.*

/**
 * Created by  范朝波 on 2018/1/28.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class CategoryAdapter(private val items: List<String>): RecyclerView.Adapter<CategoryAdapter.IndexHolder>() {

    inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            val intent = Intent()
            intent.putExtra("CATEGORY", items[adapterPosition])
            (v.context as Activity).setResult(Activity.RESULT_OK, intent)
            (v.context as Activity).finish()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IndexHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.add_category_item, parent, false)
        return IndexHolder(view)

    }

    override fun onBindViewHolder(holder: IndexHolder?, position: Int) {
        holder!!.itemView.category_text.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}