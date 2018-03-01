package com.scujcc.dada.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager

import com.scujcc.dada.R
import com.scujcc.dada.main.fragment.Guide4
import com.scujcc.dada.main.fragment.Guide1
import com.scujcc.dada.main.fragment.Guide3
import com.scujcc.dada.main.fragment.Guide2
import kotlinx.android.synthetic.main.activity_guide.*

import java.util.ArrayList

/**
 * Created by  范朝波 on 2018/1/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class GuideActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //将屏幕设置为全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_guide)

        viewpager.adapter = GuideViewPagerAdapter(supportFragmentManager, showView())

    }
    
    private fun showView(): List<Fragment> {
        val listView = ArrayList<Fragment>()
        listView.add(Guide1())
        listView.add(Guide2())
        listView.add(Guide3())
        listView.add(Guide4())
        return listView
    }
}
