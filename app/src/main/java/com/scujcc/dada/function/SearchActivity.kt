package com.scujcc.dada.function

import android.app.Activity
import android.os.Bundle
import com.scujcc.dada.R
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        buttonClick()
    }

    private fun buttonClick() {
        location.setOnClickListener {
            //地区选择
        }
        cancel.setOnClickListener { finish() }
    }
}
