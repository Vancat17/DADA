package com.scujcc.dada.function.stroke

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.scujcc.dada.R
import kotlinx.android.synthetic.main.user_stroke.*

class StrokeActivity : AppCompatActivity() {

    private var mStrokeItems : MutableList<StrokeItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的行程"
        setContentView(R.layout.user_stroke)

        mStrokeItems = ArrayList()

        addData()
        if (mStrokeItems!!.size != 0) {

            val lp = no_stroke.layoutParams
            lp.height = 0
            no_stroke.layoutParams = lp

            stroke_recycle.setHasFixedSize(true)
            stroke_recycle.layoutManager = LinearLayoutManager(applicationContext)
            stroke_recycle.adapter = StrokeAdapter(mStrokeItems!!)
        }
    }

    private fun addData() {
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))
        mStrokeItems!!.add(StrokeItem("10:89:89","游泳", "电子科大游泳馆", true))

    }
}
