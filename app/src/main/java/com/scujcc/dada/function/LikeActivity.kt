package com.scujcc.dada.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.scujcc.dada.FCBRandom

import com.scujcc.dada.R
import com.scujcc.dada.content.ContentItem
import com.scujcc.dada.content.ContentMainAdapter
import kotlinx.android.synthetic.main.user_activity_like.*
import java.util.ArrayList

class LikeActivity : AppCompatActivity() {

    private  var mContentItems: MutableList<ContentItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的收藏"
        setContentView(R.layout.user_activity_like)

        mContentItems = ArrayList()

        like_recycler.setHasFixedSize(true)
        like_recycler.layoutManager = LinearLayoutManager(applicationContext)
        like_recycler.adapter = ContentMainAdapter(this.mContentItems!!)
    }
}
