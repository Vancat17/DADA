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

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentMainFragment : Fragment() {

    private var mPage: Int = 0
    private lateinit var mContentItem: ContentItem

    private lateinit var mContentItems: MutableList<ContentItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContentItems = ArrayList()

        mPage = arguments.getInt(ARG_CONTENT_PAGE)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.content_main_recycler,container,false)
        view.content_main_recycler.setHasFixedSize(true)
        view.content_main_recycler.layoutManager = LinearLayoutManager(activity)
        view.content_main_recycler.adapter = ContentMainAdapter(this.mContentItems)

        view.content_refresh!!.setColorSchemeResources(R.color.colorPrimary)

        //首次进入刷新
        view.content_refresh.measure(0,0)
        view.content_refresh.isRefreshing = true

        //网络请求
        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build()

        val request = retrofit.create<GetRequest>(GetRequest::class.java)
        try {

            val call = request.getAllContent()
            call.enqueue(object : Callback<List<Content>> {
                override fun onResponse(call: Call<List<Content>>?, response: Response<List<Content>>?) {
                    Log.w("Test", "加载成功")
                    for (item in response!!.body()) {
                        mContentItem = ContentItem(item.contentId, R.drawable.download, "FCB", item.topic, item.tag, item.date, item.location, item.total, item.price, item.content)
                        mContentItems.add(mContentItem)
                    }
                    view.content_main_recycler.adapter.notifyDataSetChanged()
                    view.content_refresh.isRefreshing = false
                }
                override fun onFailure(call: Call<List<Content>>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

        } catch (ignored: Exception) {

        }

        //手动刷新监听
        view.content_refresh.setOnRefreshListener {

            try {
                val call = request.getAllContent()
                call.enqueue(object : Callback<List<Content>> {
                    override fun onResponse(call: Call<List<Content>>?, response: Response<List<Content>>?) {
                        Log.w("Test", "加载成功")
                        mContentItems = ArrayList()
                        for (item in response!!.body()) {
                            mContentItem = ContentItem(item.contentId, R.drawable.download, "FCB", item.topic, item.tag, item.date, item.location, item.total, item.price, item.content)
                            mContentItems.add(0,mContentItem)
                        }

                        view.content_main_recycler.adapter.notifyDataSetChanged()
                        view.content_refresh.isRefreshing = false
                    }

                    override fun onFailure(call: Call<List<Content>>?, t: Throwable?) {
                    }
                })
            } catch (ignored: Exception) { }
            if (view.content_refresh.isRefreshing) {
                view.content_refresh.isRefreshing = false
            }
        }
        return view
    }

    companion object {

        private val ARG_CONTENT_PAGE = "ARG_CONTENT_PAGE"

        fun newInstance(page: Int): ContentMainFragment {
            val args = Bundle()
            args.putInt(ARG_CONTENT_PAGE, page)
            val pageFragment = ContentMainFragment()
            pageFragment.arguments = args
            return pageFragment
        }
    }
}