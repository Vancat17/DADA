package com.scujcc.dada.add

import android.annotation.SuppressLint
import android.app.Activity
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

        buttonClick()
    }

    @SuppressLint("SimpleDateFormat")

    private fun buttonClick() {
        //用当前时间做Id永不重复

        add_button.setOnClickListener {
            val date = Date(System.currentTimeMillis())
            val contentId = SimpleDateFormat("yyyyMMddHHmmss").format(date)
            ContentItem(contentId,R.drawable.images1,"范朝波",add_topic.text.toString(),add_tag.text.toString(), "IOIO", add_location.text.toString(), 9,9.99, add_content.text.toString(),false).save()
            Toast.makeText(applicationContext,"发布成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
