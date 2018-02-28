package com.scujcc.dada.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.guide_fragment.view.*


/**
 * Created by  范朝波 on 2016/11/12.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */


class Guide3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):View? {
        val view = inflater!!.inflate(R.layout.guide_fragment, container, false)
        view.fragment_background!!.setBackgroundResource(R.mipmap.guide_3)
        return view
    }
}
