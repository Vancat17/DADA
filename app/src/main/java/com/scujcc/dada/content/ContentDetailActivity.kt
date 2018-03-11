package com.scujcc.dada.content

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.avos.avoscloud.im.v2.AVIMClient
import com.avos.avoscloud.im.v2.AVIMException
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback
import com.scujcc.dada.R
import com.scujcc.dada.chatkit.LCChatKit
import com.scujcc.dada.chatkit.activity.LCIMConversationActivity
import com.scujcc.dada.chatkit.utils.LCIMConstants
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.GetRequest
import com.scujcc.dada.helper.User
import com.scujcc.dada.pay.PayActivity
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.toolbar_header.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentDetailActivity : Activity() {

    private lateinit var content: Content
    private lateinit var contentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)
        window.statusBarColor = Color.TRANSPARENT
        contentId = intent.getSerializableExtra("CONTENT_DETAIL") as String

        getData()

        //首次进入刷新
        detail_refresh.measure(0,0)
        detail_refresh.isRefreshing = true

        detail_refresh.setOnRefreshListener {
            getData()
        }
        buttonClick()
    }

    private fun getData() {

        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build()

        val request = retrofit.create<GetRequest>(GetRequest::class.java)
        try {

            val call = request.getContent(contentId)
            call.enqueue(object : Callback<Content> {
                override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                    Log.w("Test", "加载成功")
                    val item = response!!.body()
                    content = Content(item.contentId, item.contentId, item.image, item.date, item.now, item.total, item.location, item.tag, item.topic, item.price,item.name,item.content,item.avatar,item.score)
                    detail_recycler.setHasFixedSize(true)
                    detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
                    detail_recycler.adapter = ContentDetailAdapter(content)
                    detail_refresh.isRefreshing = false
                }
                override fun onFailure(call: Call<Content>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

        } catch (ignored: Exception) {

        }
    }

    private fun buttonClick() {
        val user = DataSupport.findLast(User::class.java)
        left_button.setOnClickListener { finish() }
        right_button.setOnClickListener { Toast.makeText(applicationContext, "分享", Toast.LENGTH_SHORT).show() }
        like_button.setOnClickListener { }
        talk_button.setOnClickListener {

            //开启聊天
            LCChatKit.getInstance().open(user.name, object : AVIMClientCallback() {
                override fun done(p0: AVIMClient?, p1: AVIMException?) {

                    if (null == p1) {
                        val intent = Intent(applicationContext, LCIMConversationActivity::class.java).apply {
                            putExtra(LCIMConstants.PEER_ID, content.name)
                            putExtra("CHAT_CONTENT",content)
                        }
                        startActivity(intent)
                    }
                }
            })
        }
        join_button.setOnClickListener {
            val intent = Intent(applicationContext, PayActivity::class.java)
            intent.putExtra("JOIN", content)
            startActivity(intent)
        }
    }
}

