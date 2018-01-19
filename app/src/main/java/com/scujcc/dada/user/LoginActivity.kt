package com.scujcc.dada.user

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.R.id.phone
import com.scujcc.dada.main.MainActivity
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        window.statusBarColor = R.color.colorDarkGray

        buttonClick()
    }

    private fun buttonClick() {
        area_button.setOnClickListener { Toast.makeText(applicationContext,"地区选择按钮", Toast.LENGTH_SHORT).show() }
        phone_delete_button.setOnClickListener {
            phone.text = null
        }
        password_delete_button.setOnClickListener {
            password.text = null
        }
        code_login_button.setOnClickListener { Toast.makeText(applicationContext,"验证码登录", Toast.LENGTH_SHORT).show() }
        login_button.setOnClickListener {

//            if (formatJudgment()) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
//            }
        }
        protocol_button.setOnClickListener { Toast.makeText(applicationContext,"协议", Toast.LENGTH_SHORT).show() }
        forget_button.setOnClickListener { Toast.makeText(applicationContext,"忘记密码", Toast.LENGTH_SHORT).show() }
    }

    private fun formatJudgment(): Boolean {
        val phone = phone.text
        val password = password.text.toString()

        //此处进行验证
        return when {
            phone.length != 11 -> {
                Toast.makeText(applicationContext,"手机号不合法", Toast.LENGTH_SHORT).show()
                false
            }
            password == "" -> {
                Toast.makeText(applicationContext,"密码不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }

        //验证成功后将用户信息保存到本地
    }
}
