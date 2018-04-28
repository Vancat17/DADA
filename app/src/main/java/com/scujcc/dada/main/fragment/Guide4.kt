package com.scujcc.dada.main.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaopiz.kprogresshud.KProgressHUD
import com.scujcc.dada.R
import com.scujcc.dada.user.activity.LoginActivity
import kotlinx.android.synthetic.main.guide_fragment.view.*

/**
 * Created by  范朝波 on 2016/11/12.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class Guide4 : Fragment() {

    private var hud: KProgressHUD? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.guide_fragment, container, false)
        view.fragment_background!!.setBackgroundResource(R.mipmap.guide_4)
        view.start_button.visibility = View.VISIBLE
        view.start_button.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            hud = KProgressHUD.create(view.context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("搭搭正在加载，客官请稍后")
                    .show()
            activity.finish()
        }
        return view
    }
}