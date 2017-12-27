package com.scujcc.dada.function

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.scujcc.dada.R

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "我的钱包"
        setContentView(R.layout.user_activity_wallet)
    }

    inner class WalletAdapter : RecyclerView.Adapter<WalletAdapter.IndexHolder>() {

        /**
         * 此部分待优化, 暂时无解
         */
        open inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        inner class OneHolder(itemView: View) : IndexHolder(itemView)

        inner class TwoHolder(itemView: View) : IndexHolder(itemView)

        inner class ThreeHolder(itemView: View) : IndexHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletAdapter.IndexHolder? {
            return null
        }

        override fun onBindViewHolder(holder: WalletAdapter.IndexHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 0
        }
    }
}
