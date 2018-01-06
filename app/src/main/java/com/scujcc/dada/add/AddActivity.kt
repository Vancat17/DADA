package com.scujcc.dada.add

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import kotlinx.android.synthetic.main.add_activity.*
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
        //用当前时间做Id永不重复

        add_button.setOnClickListener {

            if (judgment()) {
                val date = Date(System.currentTimeMillis())
                val contentId = SimpleDateFormat("yyyyMMddHHmmss").format(date)
                ContentItem(contentId, 1, "范朝波", add_topic.text.toString(), add_tag.text.toString(), "IOIO", add_location.text.toString(), 9, 9.99, add_content.text.toString(), false).save()
                Toast.makeText(applicationContext, "发布成功", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, "发布失败", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun judgment() : Boolean {
        if (add_topic.text == null) {
            return false
        }
        if (add_content.text == null) {
            return false
        }
        if (add_tag.text == "分类") {
            return false
        }
        return add_price.text != "价格"
    }
}
