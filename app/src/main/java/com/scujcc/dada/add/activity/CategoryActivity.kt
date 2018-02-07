package com.scujcc.dada.add.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scujcc.dada.R
import com.scujcc.dada.common.dateselector.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.add_category_activity.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_category_activity)

        val items = arrayListOf("拼车","骑行","团购","兼职","游戏","运动","羽毛球","篮球","旅行","111","222","33","444","444","55","666","777","777")
        rv_category.setHasFixedSize(true)
        rv_category.layoutManager = LinearLayoutManager(applicationContext)
        rv_category.adapter = CategoryAdapter(items)
    }
}
