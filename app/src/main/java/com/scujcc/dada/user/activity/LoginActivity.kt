package com.scujcc.dada.user.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD
import com.scujcc.dada.R
import com.scujcc.dada.helper.GetRequest
import com.scujcc.dada.helper.User
import com.scujcc.dada.main.MainActivity
import kotlinx.android.synthetic.main.login_activity.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
class LoginActivity : AppCompatActivity() {

    private var hud: KProgressHUD? = null

    @SuppressLint("ResourceAsColor", "ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        window.statusBarColor = R.color.colorDarkGray

        val pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        phone.setText(pref.getString("phone",""))
        password.setText(pref.getString("password",""))

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
            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("正在登陆...")
                    .show()
            login()
        }
        protocol_button.setOnClickListener { Toast.makeText(applicationContext,"协议", Toast.LENGTH_SHORT).show() }
        forget_button.setOnClickListener { Toast.makeText(applicationContext,"忘记密码", Toast.LENGTH_SHORT).show() }
    }

    private fun checkPhone(): Boolean {
        val regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,1,2,5-9])|(177))\\d{8}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(phone.text)

        if (!matcher.matches()) {
            Toast.makeText(applicationContext,"手机号不合法", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.text.length < 8) {
            Toast.makeText(applicationContext,"密码至少8位", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

     @SuppressLint("ApplySharedPref")
     private  fun login() {
        //保存用户名和密码
        val editor = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
        editor.putString("phone", phone.text.toString().trim())
        editor.putString("password", password.text.toString().trim())
        editor.commit()


        if (checkPhone()) {

            val retrofit = Retrofit.Builder()
                    .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build()

            val request = retrofit.create<GetRequest>(GetRequest::class.java)

            try {
                val call = request.getUser(phone.text.toString())
                call.enqueue(object : Callback<User> {

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        DataSupport.deleteAll(User::class.java)

                        if (null != response.body()) {
                            val item = response.body()
                            val user = User(item.userId, item.avatar,item.name,item.phone,item.sex,item.level,item.verified,item.sesame,item.age,item.job,item.company,item.school,item.sign,item.address)
                            user.save()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(applicationContext,"用户不存在", Toast.LENGTH_SHORT).show()
                            hud!!.dismiss()
                        }
                        Log.w("LoginActivity", "用户请求成功")
                    }
                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Log.w("LoginActivity", "用户请求失败")
                    }
                })
            } catch (ignored: Exception) {
                Toast.makeText(applicationContext,"网络异常", Toast.LENGTH_SHORT).show()
                hud!!.dismiss()
            }

        } else {
        }
    }
}
