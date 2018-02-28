package com.scujcc.dada.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import com.scujcc.dada.content.ContentMainAdapter
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.GetRequest
import com.scujcc.dada.helper.User
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.user_activity_like.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class LikeActivity : AppCompatActivity() {

    private var mContentItems: MutableList<ContentItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的收藏"
        setContentView(R.layout.user_activity_like)

        mContentItems = ArrayList()

        getData()

        like_recycler.setHasFixedSize(true)
        like_recycler.layoutManager = LinearLayoutManager(applicationContext)
        like_recycler.adapter = ContentMainAdapter(this.mContentItems!!)

    }

    private fun getData() {
        val user = DataSupport.findLast(User::class.java)

        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build()

        val request = retrofit.create<GetRequest>(GetRequest::class.java)

        try {

            val call = request.getAllLikes(user.userId)
            call.enqueue(object : Callback<List<Content>>{
                override fun onFailure(call: Call<List<Content>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Content>>?, response: Response<List<Content>>?) {
                }

            })
        } catch (ignored: Exception) {
            Toast.makeText(applicationContext, "网络异常", Toast.LENGTH_SHORT).show()
        }

    }

}
