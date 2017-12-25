package com.scujcc.dahuo.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scujcc.dahuo.R
import kotlinx.android.synthetic.main.content_main_recycler.view.*

import java.util.ArrayList

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class PageFragment : Fragment() {

    private var mPage: Int = 0

    private  var mContentItems: MutableList<ContentItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContentItems = ArrayList()
        mPage = arguments.getInt(ARG_CONTENT_PAGE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.content_main_recycler,container,false)
        addData()
        view.content_main_recycler.setHasFixedSize(true)
        view.content_main_recycler.layoutManager = LinearLayoutManager(activity)
        view.content_main_recycler.adapter = ContentMainAdapter(this.mContentItems!!)

        view.content_refresh.setOnRefreshListener {
            addData()
            view.content_main_recycler.adapter.notifyDataSetChanged()
            view.content_refresh.isRefreshing = false
        }
        return view
    }

    private fun addData() {
        mContentItems!!.add(ContentItem(R.drawable.download2, "阿花", "晚上去打篮球", "篮球", "12月20日 上午10:30", "四川大学锦城学院", 3, 0, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download4, "小嗒", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 4, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download3, "露葵", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 7, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download5, "抠破", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 2, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download3, "蓝莓", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 4, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download2, "命令", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 5, 100, "一起回家吧 节约大把的钱"))
//        mContentItems!!.add(ContentItem(R.drawable.download5, "面积", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 1, 100, "一起回家吧 节约大把的钱"))
//        mContentItems!!.add(ContentItem(R.drawable.download4, "哦怕", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 9, 100, "一起回家吧 节约大把的钱"))
//        mContentItems!!.add(ContentItem(R.drawable.download2, "突然", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 8, 100, "一起回家吧 节约大把的钱"))
//        mContentItems!!.add(ContentItem(R.drawable.download3, "都是", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 5, 100, "一起回家吧 节约大把的钱"))

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