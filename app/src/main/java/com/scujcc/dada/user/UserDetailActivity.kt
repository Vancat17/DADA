package com.scujcc.dada.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scujcc.dada.R

import java.util.ArrayList

import kotlinx.android.synthetic.main.user_detail_activity.*
import kotlinx.android.synthetic.main.user_detail_function.view.*

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailActivity : Activity() {


    private var mFunctionItems: MutableList<FunctionItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_activity)

        initializeData()
        rv_user_detail.setHasFixedSize(true)
        rv_user_detail.layoutManager = LinearLayoutManager(applicationContext)
        rv_user_detail.adapter = Adapter(this.mFunctionItems!!)

        back_button.setOnClickListener { finish() }

        edit_button.setOnClickListener {
            val intent = Intent(this@UserDetailActivity, UserDetailEditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeData() {
        mFunctionItems = ArrayList()
        mFunctionItems!!.add(FunctionItem("普通会员", "进入会员主页", R.drawable.ic_vip))
        mFunctionItems!!.add(FunctionItem("实名认证", "未认证", R.drawable.ic_verified))
        mFunctionItems!!.add(FunctionItem("芝麻信用认证", "未认证", R.drawable.ic_sesame))
    }

    class Adapter(private val mItems: List<FunctionItem>) : RecyclerView.Adapter<Adapter.IndexHolder>() {

        open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        inner class HeaderHolder(itemView: View) : IndexHolder(itemView)

        inner class FunctionHolder(itemView: View) : IndexHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexHolder {

            val holder: IndexHolder
            val view: View
            when (viewType) {
                HEADER_TYPE -> {
                    view = LayoutInflater.from(parent.context).inflate(R.layout.user_detail_header, parent, false)
                    holder = HeaderHolder(view)
                }
                else -> {
                    view = LayoutInflater.from(parent.context).inflate(R.layout.user_detail_function, parent, false)
                    holder = FunctionHolder(view)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: IndexHolder, position: Int) {
            when (getItemViewType(position)) {
                HEADER_TYPE -> {
                }
                else -> {
                    val functionHolder = holder as FunctionHolder

                    val pos = if (position - 1 > 0) position - 1 else 0
                    functionHolder.itemView.detail_function_title.text = mItems[pos].title
                    functionHolder.itemView.detail_function_subtitle.text = mItems[pos].subTitle
                    functionHolder.itemView.detail_function_image.setImageResource(mItems[pos].imageId)
                }
            }
        }

        override fun getItemCount(): Int {
            return mItems.size + 1
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> HEADER_TYPE
                else -> DEFAULT_TYPE
            }
        }

        companion object {

            private val HEADER_TYPE = 1
            private val DEFAULT_TYPE = 2
        }
    }
}
