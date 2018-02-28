package com.scujcc.dada.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scujcc.dada.R
import com.scujcc.dada.helper.User
import com.scujcc.dada.user.LoginActivity
import org.litepal.crud.DataSupport

/**
 * Created by  范朝波 on 2018/1/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class SplashActivity : AppCompatActivity() {

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //判断是否首次启动
        val pref = getSharedPreferences("firstIn",0)
        val first = pref.getBoolean("FIRST", true)

        if (first) {
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)

            val editor = pref.edit()
            editor.putBoolean("FIRST", false)
            editor.commit()
            finish()
        } else {

            try {
                val user = DataSupport.findLast(User::class.java)
                if (user == null)
                {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } catch (ex: Exception){}

        }
    }
}
