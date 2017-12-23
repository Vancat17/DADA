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
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "AAA", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "BBB", 1, "10/10 1:30", "约足球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "CCC", 1, "10/10 4:30", "约火锅", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "JJJ", 1, "10/10 7:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "PPP", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "AOO", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "LPL", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "AAA", 1, "10/10 9:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "MKL", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "UIO", 1, "10/10 2:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "HHH", 1, "10/10 2:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "GTF", 1, "10/10 1:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "AAA", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "FTD", 1, "10/10 8:40", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "HOB", 1, "10/10 6:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "AXA", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "BHU", 1, "10/10 8:10", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))
        mContentItems!!.add(ContentItem(R.drawable.ic_user_photo, "FGT", 1, "10/10 3:10", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4))

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
