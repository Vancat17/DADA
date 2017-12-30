package com.scujcc.dada.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.scujcc.dada.R

import java.util.ArrayList

import kotlinx.android.synthetic.main.user_detail_activity.*
import kotlinx.android.synthetic.main.user_detail_function.view.*
import kotlinx.android.synthetic.main.user_detail_header.view.*

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class UserDetailActivity : Activity() {


    private var mFunctionItems: MutableList<FunctionItem>? = null
    private var mUser: UserItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_activity)

        mUser = intent.getSerializableExtra("USER_DETAIL") as UserItem

        initializeData()
        rv_user_detail.setHasFixedSize(true)
        rv_user_detail.layoutManager = LinearLayoutManager(applicationContext)
        rv_user_detail.adapter = Adapter(this.mFunctionItems!!,mUser!!)

        back_button.setOnClickListener { finish() }

        edit_button.setOnClickListener {
            val intent = Intent(this@UserDetailActivity, UserDetailEditActivity::class.java)
            intent.putExtra("USER_DETAIL_EDIT", mUser)
            startActivityForResult(intent,0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val bundle = data!!.extras
        val user = bundle.get("DETAIL_RESULT") as UserItem
        Log.w("Test", user.sex.toString())
        mUser = user
    }

    private fun initializeData() {

        mFunctionItems = ArrayList()
        mFunctionItems!!.add(FunctionItem(vipLevel(mUser!!.vip), "进入会员主页", R.drawable.ic_vip))
        mFunctionItems!!.add(FunctionItem("实名认证", isNo(mUser!!.isVerify), R.drawable.ic_verified))
        mFunctionItems!!.add(FunctionItem("芝麻信用认证", isNo(mUser!!.isSesame), R.drawable.ic_sesame))
    }

    private fun vipLevel(level: Int): String {
        return when(level) {
            0 -> "普通会员"
            1 -> "黄金会员"
            2 -> "钻石会员"
            else -> "我的会员"
        }
    }

    private fun isNo(isNo: Boolean): String {
        return when(isNo) {
            false -> "未认证"
            true -> "已认证"
        }
    }

    class Adapter(private val mItems: List<FunctionItem>,private val user: UserItem) : RecyclerView.Adapter<Adapter.IndexHolder>() {

        open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        inner class HeaderHolder(itemView: View) : IndexHolder(itemView)

        inner class FunctionHolder(itemView: View) : IndexHolder(itemView), View.OnClickListener {

            init {
                itemView.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                Toast.makeText(v!!.context, "HHH", Toast.LENGTH_SHORT).show()
            }

        }

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
                    val headerHolder = holder as HeaderHolder
                    headerHolder.itemView.user_photo.setImageResource(user.photoId!!)
                    headerHolder.itemView.user_name.text = user.name
                    headerHolder.itemView.user_signature.text = if (user.sign != null) user.sign else "还没有签名哦，简单介绍一下自己吧"
                    headerHolder.itemView.user_job.text = if (user.job != null) user.job else "未设置行业"
                    when (user.sex) {
                        0 -> { holder.itemView.user_sex.setImageResource(R.drawable.ic_man)}
                        1 -> { holder.itemView.user_sex.setImageResource(R.drawable.ic_woman)}
                    }
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
