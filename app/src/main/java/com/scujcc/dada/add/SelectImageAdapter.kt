package com.scujcc.dada.add

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.avos.avoscloud.AVOSCloud.applicationContext
import com.bumptech.glide.Glide
import com.donkingliang.imageselector.utils.ImageSelectorUtils
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.adapter_image.view.*
import java.io.File
import java.util.*

/**
 * Created by  范朝波 on 2018/1/23.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class SelectImageAdapter(private val mContext: Activity) : RecyclerView.Adapter<SelectImageAdapter.ViewHolder>() {

    private var images: ArrayList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == images.size) {
                ImageSelectorUtils.openPhoto(mContext, REQUEST_CODE, false, 9)
            }
        }

        var ivImage: ImageView = itemView.findViewById(R.id.iv_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.adapter_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectImageAdapter.ViewHolder, position: Int) {
        if (position == images.size) {
            holder.itemView.delete_button.visibility = View.GONE
            Glide.with(mContext).load(R.drawable.add_pic).into(holder.ivImage)
        } else {
            val image = images[position]
            holder.itemView.delete_button.visibility = View.VISIBLE
            Glide.with(mContext).load(File(image)).into(holder.ivImage)
        }

        holder.itemView.delete_button.setOnClickListener {
            images.removeAt(position)
            refresh(images)
        }
    }

    override fun getItemCount(): Int {
        return images.size + 1
    }

    fun refresh(images: ArrayList<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    companion object {
        val REQUEST_CODE = 0x00000011
    }
}
