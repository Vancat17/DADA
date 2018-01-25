package com.scujcc.dada.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.donkingliang.imageselector.utils.ImageSelectorUtils
import com.scujcc.dada.R
import com.scujcc.dada.add.SelectImageAdapter.Companion.REQUEST_CODE
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.KeyboardUtil
import com.scujcc.dada.helper.PostRequest
import kotlinx.android.synthetic.main.add_activity.*
import kotlinx.android.synthetic.main.price_keyboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddActivity : Activity() {

    private var adapter: SelectImageAdapter? = null
    private var images: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        window.statusBarColor = Color.WHITE

        adapter = SelectImageAdapter(this)
        rv_image.adapter = adapter
        rv_image.layoutManager = GridLayoutManager(this, 4)
        buttonClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && data != null) {
            val images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT)
            for (image in images) {
                this.images.add(image)
            }
            adapter!!.refresh(this.images)
        }
    }
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility", "SimpleDateFormat")
    private fun buttonClick() {

        val keyboardUtil = KeyboardUtil(this)
        keyboardUtil.setOnOkClick {
            if (validate()) {
                ll_price_select!!.visibility = View.GONE
                add_button.visibility = View.VISIBLE
                add_price.text = "¥" + et_price.text + "/人，" +  "  原价 ¥" + et_original_price.text + "/人," + " 人数 :" + et_num.text + "/" + et_totalNum.text
            }
        }
        keyboardUtil.setOnCancelClick {
            ll_price_select!!.visibility = View.GONE
            add_button.visibility = View.VISIBLE
        }

        ll_price!!.setOnClickListener {
            keyboardUtil.attachTo(et_price)
            et_price.requestFocus()
            ll_price_select!!.visibility = View.VISIBLE
            add_button.visibility = View.INVISIBLE
        }
        et_price.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_price)
            false
        }
        et_original_price.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_original_price)
            false
        }
        et_num.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_num)
            false
        }
        et_totalNum.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_totalNum)
            false
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
                    val content = Content(contentId, contentId,"image", 0, 4,add_location.text.toString(), add_tag.text.toString(), add_topic.text.toString(), 18.99,add_content.text.toString())
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

    private fun validate(): Boolean {
        return when {
            et_price!!.text.toString() == "" -> {
                Toast.makeText(application, "价格不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_original_price!!.text.toString() == "" -> {
                Toast.makeText(application, "原价不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_totalNum!!.text.toString() == "" -> {
                Toast.makeText(application, "总人数不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_num.text.toString() >= et_totalNum.text.toString() -> {
                Toast.makeText(application, "现有人数不能大于总人数", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}