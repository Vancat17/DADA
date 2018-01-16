package com.scujcc.dada.add

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.PostRequest
import kotlinx.android.synthetic.main.add_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        window.statusBarColor = Color.WHITE
        buttonClick()
    }

    @SuppressLint("SimpleDateFormat")

    private fun buttonClick() {

        add_price.setOnClickListener {

            val priceArray = arrayOf("9.9", "19.99","90.99")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(priceArray, { dialog, which ->
                add_price.text = priceArray[which]
                dialog.dismiss()
            })
            builder.show()
        }

        add_tag.setOnClickListener {

            val sexArray = arrayOf("拼车", "羽毛球","荒野行动", "拼单", "跑步")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                add_tag.text = sexArray[which]
                dialog.dismiss()
            })
            builder.show()
        }

        add_time.setOnClickListener {

            val sexArray = arrayOf("拼车", "羽毛球")
            val builder = AlertDialog.Builder(this)// 自定义对话框
            builder.setItems(sexArray, { dialog, which ->
                add_time.text = sexArray[which]
                dialog.dismiss()
            })
            builder.show()
        }
        //用当前时间做Id永不重复

        add_button.setOnClickListener {

            if (judgment()) {

                val retrofit = Retrofit.Builder()
                        .baseUrl("http://120.79.19.183:8080/") // 设置 网络请求 Url
                        .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                        .build()

                val request = retrofit.create<PostRequest>(PostRequest::class.java)
                try {

                    val date = Date(System.currentTimeMillis())
                    val contentId = SimpleDateFormat("yyyyMMddHHmmss").format(date)
                    val content = Content(contentId,"image", 0, 4,add_location.text.toString(), add_tag.text.toString(), add_topic.text.toString(), 18.99,add_content.text.toString())
                    val call = request.postContent(content)
                    call.enqueue(object : Callback<Content> {
                        override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                        }
                        override fun onFailure(call: Call<Content>?, t: Throwable?) {
                        }
                    })
                } catch (ig : Exception) {

                }
                Toast.makeText(applicationContext, "发布成功", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    private fun judgment() : Boolean {

        if (add_topic.text == null) {
            Toast.makeText(applicationContext, "标题不能为空哦", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_content.text == null) {
            Toast.makeText(applicationContext, "内容不能为空哦", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_tag.text == "分类") {
            Toast.makeText(applicationContext, "请选择分类", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_price.text == "价格") {
            Toast.makeText(applicationContext, "请设置价格", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_time.text == "时间") {
            Toast.makeText(applicationContext, "请设置价格", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
