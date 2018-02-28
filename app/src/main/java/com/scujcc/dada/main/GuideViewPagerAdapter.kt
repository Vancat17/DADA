package com.scujcc.dada.main


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by  范朝波 on 2018/1/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class GuideViewPagerAdapter internal constructor(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(arg0: Int): Fragment {
        return fragmentList[arg0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}
