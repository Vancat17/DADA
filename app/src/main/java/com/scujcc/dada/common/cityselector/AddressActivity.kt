package com.scujcc.dada.common.cityselector

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.scujcc.dada.R
import com.scujcc.dada.common.cityselector.adapter.AddressAdapter
import com.scujcc.dada.common.cityselector.utils.AddressUtils
import kotlinx.android.synthetic.main.add_address_activity.*

class AddressActivity : AppCompatActivity() {

    private var locationClient: AMapLocationClient? = null
    private var locationOption: AMapLocationClientOption? = null
    private val defaultOption: AMapLocationClientOption
        get() {
            val mOption = AMapLocationClientOption()
            mOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            mOption.isGpsFirst = false
            mOption.httpTimeOut = 30000
            mOption.interval = 2000
            mOption.isNeedAddress = true
            mOption.isOnceLocation = false
            mOption.isOnceLocationLatest = false
            AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP)
            mOption.isSensorEnable = false
            mOption.isWifiScan = true
            mOption.isLocationCacheEnable = true
            return mOption
        }
    /**
     * 定位监听
     */
    private var locationListener: AMapLocationListener = object : AMapLocationListener {

        private var nowLocation: String? = null

        @SuppressLint("SetTextI18n")
        override fun onLocationChanged(location: AMapLocation?) {
            if (null != location) {

                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.errorCode == 0) {

                    nowLocation = location.city + " " + location.district + " " + location.street

                    Log.w("Test", "定位成功")

                } else {

                    nowLocation = "定位失败"
                }
            }
            Log.w("Test", nowLocation)
            text_view.text = nowLocation
        }
    }

    private var address: List<Any> = ArrayList()
    private var adapter: AddressAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address_activity)

        //初始化client
        locationClient = AMapLocationClient(this.applicationContext)
        locationOption = defaultOption
        //设置定位参数
        locationClient!!.setLocationOption(locationOption)
        // 设置定位监听
        locationClient!!.setLocationListener(locationListener)

        startLocation()

        address = AddressUtils().getProvinceList(applicationContext,"pcas.json")

        Handler().postDelayed({

            rv_address.setHasFixedSize(true)
            rv_address.layoutManager = LinearLayoutManager(applicationContext)
            adapter = AddressAdapter(text_view.text.toString(), address)
            rv_address.adapter = adapter
            destroyLocation()

        }, 500)
    }

    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun startLocation() {

        //根据控件的选择，重新设置定位参数
        Log.w("Test", "开始定位")
        // 设置定位参数
        locationClient!!.setLocationOption(locationOption)
        // 启动定位
        locationClient!!.startLocation()
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun stopLocation() {
        // 停止定位
        locationClient!!.stopLocation()
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient!!.onDestroy()
            locationClient = null
            locationOption = null
        }
    }
}
