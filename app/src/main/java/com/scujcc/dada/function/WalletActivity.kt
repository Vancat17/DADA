package com.scujcc.dada.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.scujcc.dada.R
import kotlinx.android.synthetic.main.user_activity_wallet.*

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的钱包"
        setContentView(R.layout.user_activity_wallet)
        buttonClick()
    }

    private fun buttonClick() {
        recharge_button.setOnClickListener { Toast.makeText(applicationContext, "充值", Toast.LENGTH_SHORT).show() }
        detail_button.setOnClickListener { Toast.makeText(applicationContext, "充值详情", Toast.LENGTH_SHORT).show() }
        withdraw_button.setOnClickListener { Toast.makeText(applicationContext, "提现", Toast.LENGTH_SHORT).show() }
        money_detail_button.setOnClickListener { Toast.makeText(applicationContext, "提现明细", Toast.LENGTH_SHORT).show() }
        coupon_detail_button.setOnClickListener { Toast.makeText(applicationContext, "优惠券详情", Toast.LENGTH_SHORT).show() }
        coupon_button.setOnClickListener { Toast.makeText(applicationContext, "使用记录", Toast.LENGTH_SHORT).show() }
    }
}
