package cn.leancloud.chatkit.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.ImageView

import com.squareup.picasso.Picasso

import java.io.File

import cn.leancloud.chatkit.R
import cn.leancloud.chatkit.utils.LCIMConstants

/**
 * Created by wli on 16/2/29.
 * 图片详情页，聊天时点击图片则会跳转到此页面
 */
class LCIMImageActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lcim_chat_image_brower_layout)
        imageView = findViewById(R.id.imageView)
        val intent = intent
        val path = intent.getStringExtra(LCIMConstants.IMAGE_LOCAL_PATH)
        val url = intent.getStringExtra(LCIMConstants.IMAGE_URL)
        if (TextUtils.isEmpty(path)) {
            Picasso.with(this).load(url).into(imageView)
        } else {
            Picasso.with(this).load(File(path)).into(imageView)
        }

    }
}
