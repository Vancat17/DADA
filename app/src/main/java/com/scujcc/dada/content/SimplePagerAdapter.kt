package com.scujcc.dada.content

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class SimplePagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }

    companion object {
        private val mTitles = arrayOf("全部", "拼车", "出行", "兼职", "运动", "外卖", "游戏", "待续")
    }
}
