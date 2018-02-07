package com.scujcc.dada.common.cityselector.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.scujcc.dada.R
import com.scujcc.dada.common.cityselector.model.CityModel
import com.scujcc.dada.common.cityselector.model.DistrictModel
import com.scujcc.dada.common.cityselector.model.ProvinceModel
import com.scujcc.dada.common.cityselector.model.StreetModel
import kotlinx.android.synthetic.main.add_address_item.view.*

/**
 * Created by  范朝波 on 2018/1/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class AddressAdapter(private var location: String?, addresses: List<Any>): RecyclerView.Adapter<AddressAdapter.IndexHolder>() {

    var temps: List<Any> = addresses
    var items: List<Any> = addresses
    var address = ""

    inner class IndexHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View) {
            val intent = Intent()
            if (adapterPosition == 0) {
                intent.putExtra("LOCATION", location)
                (v.context as Activity).setResult(Activity.RESULT_OK, intent)
                (v.context as Activity).finish()
            } else {

                val pos = if (adapterPosition >= 1) adapterPosition - 1 else 0
                when {
                    items[pos] is ProvinceModel -> {
                        val item = items[pos] as ProvinceModel
                        items  = item.childs
                        temps = items
//                        address += item.name + " "
                        notifyDataSetChanged()
                    }
                    items[pos] is CityModel -> {
                        val item = items[pos] as CityModel
                        items  = item.childs
                        temps = items
                        address += item.name + " "
                        notifyDataSetChanged()

                    }
                    items[pos] is DistrictModel -> {
                        val item = items[pos] as DistrictModel
                        items  = item.childs
                        address += item.name + " "
                        notifyDataSetChanged()
                    }
                    items[pos] is StreetModel -> {
                        val item = items[pos] as StreetModel
                        address += item.name
                        Toast.makeText(v.context,item.name, Toast.LENGTH_SHORT).show()

                        intent.putExtra("LOCATION", address)
                        (v.context as Activity).setResult(Activity.RESULT_OK, intent)
                        (v.context as Activity).finish()
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_address_item, parent, false)
        return IndexHolder(view)
    }

    override fun onBindViewHolder(holder: IndexHolder?, position: Int) {

        when {
            position == 0 -> {
                holder!!.itemView.address_text.text = location ?: "定位失败"
                holder.itemView.next.setImageResource(R.drawable.ic_location)
            }
            position < items.size -> {

                val pos = position - 1
                when {
                    items[pos] is ProvinceModel -> {

                        val item = items[pos] as ProvinceModel
                        holder!!.itemView.address_text.text = item.name
                    }
                    items[position] is CityModel -> {

                        val item = items[pos] as CityModel
                        holder!!.itemView.address_text.text = item.name
                    }
                    items[pos] is DistrictModel -> {
                        val item = items[pos] as DistrictModel
                        holder!!.itemView.address_text.text = item.name
                    }
                    items[pos] is StreetModel -> {
                        val item = items[pos] as StreetModel
                        holder!!.itemView.address_text.text = item.name
                        holder.itemView.next.visibility = View.GONE
                    }
                }
            }
            position == items.size -> when {
                items[position - 1] is ProvinceModel -> {

                    val item = items[position - 1] as ProvinceModel
                    holder!!.itemView.address_text.text = item.name
                }
                items[position - 1] is CityModel -> {

                    val item = items[position - 1] as CityModel
                    holder!!.itemView.address_text.text = item.name
                }
                items[position - 1] is DistrictModel -> {
                    val item = items[position - 1] as DistrictModel
                    holder!!.itemView.address_text.text = item.name
                }
                items[position - 1] is StreetModel -> {
                    val item = items[position - 1] as StreetModel
                    holder!!.itemView.address_text.text = item.name
                    holder.itemView.next.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {

        return items.size + 1
    }
}