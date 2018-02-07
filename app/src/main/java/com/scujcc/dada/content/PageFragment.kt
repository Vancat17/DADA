package com.scujcc.dada.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scujcc.dada.R
import com.scujcc.dada.helper.Content
import com.scujcc.dada.helper.GetRequest
import kotlinx.android.synthetic.main.content_main_recycler.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class PageFragment : Fragment() {

    private var mPage: Int = 0
    private lateinit var mContentItem: ContentItem

    private  var mContentItems: MutableList<ContentItem>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContentItems = ArrayList()

        mPage = arguments.getInt(ARG_CONTENT_PAGE)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.content_main_recycler,container,false)
        view.content_main_recycler.setHasFixedSize(true)
        view.content_main_recycler.layoutManager = LinearLayoutManager(activity)
        view.content_main_recycler.adapter = ContentMainAdapter(this.mContentItems!!)

        view.content_refresh.setOnRefreshListener {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://120.79.19.183:8080/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .build()

            val request = retrofit.create<GetRequest>(GetRequest::class.java)
            try {
                val call = request.getContent("20180131125346")
                call.enqueue(object : Callback<Content> {
                    override fun onResponse(call: Call<Content>, response: Response<Content>) {

                        Log.w("Test", "加载成功")
                        mContentItem = ContentItem(response.body().contentId, R.drawable.download, "FCB", response.body().topic, response.body().tag, response.body().date, response.body().location, response.body().totalnumber, response.body().price, response.body().content)
                        mContentItems!!.add(mContentItem)

                    }
                    override fun onFailure(call: Call<Content>, t: Throwable) {
                        Log.w("Test", "加载失败")

                    }
                })
            } catch (ignored: Exception) {

            }
            view.content_main_recycler.adapter.notifyDataSetChanged()
            view.content_refresh.isRefreshing = false
        }

        view.content_refresh!!.setColorSchemeResources(R.color.colorPrimary)

        return view
    }

    companion object {

        private val ARG_CONTENT_PAGE = "ARG_CONTENT_PAGE"

        fun newInstance(page: Int): PageFragment {
            val args = Bundle()
            args.putInt(ARG_CONTENT_PAGE, page)
            val pageFragment = PageFragment()
            pageFragment.arguments = args
            return pageFragment
        }
    }
}