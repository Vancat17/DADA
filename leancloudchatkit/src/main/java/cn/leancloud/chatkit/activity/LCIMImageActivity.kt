package cn.leancloud.chatkit.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

import com.squareup.picasso.Picasso

import java.io.File

import cn.leancloud.chatkit.R
import cn.leancloud.chatkit.utils.LCIMConstants
import kotlinx.android.synthetic.main.lcim_chat_image_brower_layout.*

/**
 * Created by wli on 16/2/29.
 * 图片详情页，聊天时点击图片则会跳转到此页面
 */
class LCIMImageActivity : Activity(){

    private var imageView: ImageView? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lcim_chat_image_brower_layout)
        window.decorView.systemUiVisibility = View.INVISIBLE
        imageView = findViewById(R.id.imageView)
        val intent = intent
        val path = intent.getStringExtra(LCIMConstants.IMAGE_LOCAL_PATH)
        val url = intent.getStringExtra(LCIMConstants.IMAGE_URL)
        if (TextUtils.isEmpty(path)) {
            Picasso.with(this).load(url).into(imageView)
        } else {
            Picasso.with(this).load(File(path)).into(imageView)
        }

        close_button.setOnClickListener { finish() }
    }
}
