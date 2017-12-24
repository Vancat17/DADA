package com.scujcc.dahuo.content

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scujcc.dahuo.R

import java.util.ArrayList

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class PageFragment : Fragment() {

    private lateinit var mContentMainRecyle: RecyclerView

    private var mPage: Int = 0

    private  var mContentItems: MutableList<ContentItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments.getInt(ARG_CONTENT_PAGE)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.content_main_recycle, container, false)
        mContentMainRecyle = view.findViewById(R.id.content_main_recyle)
        addData()
        mContentMainRecyle.setHasFixedSize(true)
        mContentMainRecyle.layoutManager = LinearLayoutManager(activity)
        mContentMainRecyle.adapter = ContentMainAdapter(this.mContentItems!!)
        return view
    }

    private fun addData() {
        mContentItems = ArrayList()
        mContentItems!!.add(ContentItem(R.drawable.download2, "阿花", "晚上去打篮球", "篮球", "12月20日 上午10:30", "四川大学锦城学院", 3, 0, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download4, "小嗒", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 4, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download3, "露葵", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 7, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download5, "抠破", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 2, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download3, "蓝莓", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 4, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download2, "命令", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 5, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download5, "面积", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 1, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download4, "哦怕", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 9, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download2, "突然", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 8, 100, "一起回家吧 节约大把的钱"))
        mContentItems!!.add(ContentItem(R.drawable.download3, "都是", "拼滴滴回泸州", "拼车", "12月20日 上午10:30", "四川大学锦城学院", 5, 100, "一起回家吧 节约大把的钱"))

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
