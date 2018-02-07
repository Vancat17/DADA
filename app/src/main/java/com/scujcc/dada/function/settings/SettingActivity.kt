package com.scujcc.dada.function.settings

import android.content.Intent
import android.graphics.Color.RED
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.githang.groundrecycleradapter.GroupItemDecoration
import com.githang.groundrecycleradapter.GroupRecyclerAdapter

import com.scujcc.dada.R
import com.scujcc.dada.user.LoginActivity
import kotlinx.android.synthetic.main.setting_item.view.*
import kotlinx.android.synthetic.main.user_activity_setting.*

@Suppress("DEPRECATION")
class SettingActivity : AppCompatActivity() {

    val teams = ArrayList<SettingItem>()
    private val members = ArrayList<SettingMember>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "设置"
        setContentView(R.layout.user_activity_setting)
        setting_recycler.layoutManager = LinearLayoutManager(this)
        val layoutInflater = LayoutInflater.from(this)

        initData()
        
        val recyclerAdapter = object : GroupRecyclerAdapter<SettingItem, SettingItemHolder, SettingTitleholder>(teams) {
            override fun onCreateGroupViewHolder(parent: ViewGroup?): SettingItemHolder {
                return SettingItemHolder(layoutInflater.inflate(R.layout.setting_title, parent, false))

            }

            override fun getChildCount(group: SettingItem?): Int {
                return group!!.members.size
            }

            override fun onBindGroupViewHolder(holder: SettingItemHolder?, groupPosition: Int) {
            }

            override fun onBindChildViewHolder(holder: SettingTitleholder?, groupPosition: Int, childPosition: Int) {
                val member = getGroup(groupPosition).members[childPosition]

                if (groupPosition == 3) {
                    holder!!.itemView.title.setTextColor(RED)
                    holder.itemView.title.width = windowManager.defaultDisplay.width
                    holder.itemView.title.gravity = Gravity.CENTER
                    holder.itemView.next.visibility = View.GONE
                }

                holder!!.itemView.title.text = member.name
                holder.itemView.subtitle.text = member.memo
            }

            override fun onCreateChildViewHolder(parent: ViewGroup?): SettingTitleholder {
                return SettingTitleholder(layoutInflater.inflate(R.layout.setting_item, parent, false))
            }
        }

        setting_recycler.adapter = recyclerAdapter

        val decoration = GroupItemDecoration(recyclerAdapter)
        decoration.setTitleDivider(ResourcesCompat.getDrawable(resources, R.drawable.divider_height_1_px, null))
        decoration.setChildDivider(ResourcesCompat.getDrawable(resources, R.drawable.divider_white_header, null))
        setting_recycler.addItemDecoration(decoration)
    }

    inner class SettingTitleholder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (adapterPosition) {
                1 -> Toast.makeText(applicationContext,"声音", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(applicationContext,"自动接收", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(applicationContext,"修改手机号", Toast.LENGTH_SHORT).show()
                5 -> Toast.makeText(applicationContext,"修改密码", Toast.LENGTH_SHORT).show()
                7 -> Toast.makeText(applicationContext,"版本", Toast.LENGTH_SHORT).show()
                8 -> Toast.makeText(applicationContext,"ABOUT", Toast.LENGTH_SHORT).show()
                10 -> {
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    inner class SettingItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private fun initData() {

        members.add(SettingMember("音效提示",""))
        members.add(SettingMember("切换语言",""))
        members.add(SettingMember("修改手机号",""))
        members.add(SettingMember("修改密码",""))
        members.add(SettingMember("版本更新","V1.0"))
        members.add(SettingMember("关于我们",""))
        members.add(SettingMember("退出登录",""))

        for (i in 0..3) {
            when (i) {
                0 -> teams.add(SettingItem("", members.subList(0,2)))
                1 -> teams.add(SettingItem("", members.subList(2,4)))
                2 -> teams.add(SettingItem("", members.subList(4,6)))
                3 -> teams.add(SettingItem("", members.subList(6,7)))
            }

        }
    }
}
