package com.scujcc.dahuo.content

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scujcc.dahuo.R
import kotlinx.android.synthetic.main.content_detail.*

class ContentDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_detail)

        content_detail_recycler.setHasFixedSize(true)
        content_detail_recycler.layoutManager = LinearLayoutManager(applicationContext)
        content_detail_recycler.adapter = ContentDetailAdapter()
    }
}
