package com.scujcc.dada.function.collection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.GetRequest
import com.scujcc.dada.helper.User
import kotlinx.android.synthetic.main.collection.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CollectionActivity : AppCompatActivity() {

    private lateinit var mCollectionItem: MutableList<CollectionItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的收藏"
        setContentView(R.layout.collection)

        mCollectionItem = ArrayList()

        getData()

        collection_recycle.setHasFixedSize(true)
        collection_recycle.layoutManager = LinearLayoutManager(applicationContext)
        collection_recycle.adapter = CollectionAdapter(this.mCollectionItem)

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
                    for (item in response!!.body()) {
//                        mCollectionItem.add(CollectionItem(item.image,))
                    }
                    no_collection.visibility = View.GONE
                }

            })
        } catch (ignored: Exception) {
            Toast.makeText(applicationContext, "网络异常", Toast.LENGTH_SHORT).show()
        }

    }

}
