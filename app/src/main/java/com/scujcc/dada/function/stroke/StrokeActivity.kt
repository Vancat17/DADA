package com.scujcc.dada.function.stroke

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import com.scujcc.dada.R
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.GetRequest
import com.scujcc.dada.helper.Stroke
import com.scujcc.dada.helper.User
import kotlinx.android.synthetic.main.user_stroke.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StrokeActivity : AppCompatActivity() {

    private var mStrokeItems : MutableList<StrokeItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的行程"
        setContentView(R.layout.user_stroke)

        mStrokeItems = ArrayList()

        getData()
        if (mStrokeItems!!.size != 0) {

            val lp = no_stroke.layoutParams
            lp.height = 0
            no_stroke.layoutParams = lp

            stroke_recycle.setHasFixedSize(true)
            stroke_recycle.layoutManager = LinearLayoutManager(applicationContext)
            stroke_recycle.adapter = StrokeAdapter(mStrokeItems!!)
        }
    }

    private fun getData() {

        val user = DataSupport.findLast(User::class.java)

        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build()

        val request = retrofit.create<GetRequest>(GetRequest::class.java)

        try {

            val call = request.getAllStrokes(user.userId)
            call.enqueue(object : Callback<List<Stroke>> {
                override fun onFailure(call: Call<List<Stroke>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Stroke>>?, response: Response<List<Stroke>>?) {

                    if (response != null) {
                        for (item in response.body()) {
                            mStrokeItems!!.add(StrokeItem(item.date,item.topic, item.location, item.finished))
                        }
                        stroke_recycle.adapter.notifyDataSetChanged()
                    }
                }
            })
        } catch (ignored: Exception) {
            Toast.makeText(applicationContext, "网络异常", Toast.LENGTH_SHORT).show()
        }


//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
//        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
    }
}
