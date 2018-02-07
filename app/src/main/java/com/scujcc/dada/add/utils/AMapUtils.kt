/**
 *
 */
@file:Suppress("NAME_SHADOWING")

package com.scujcc.dada.add.utils

import android.text.TextUtils

import com.amap.api.location.AMapLocation

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * 辅助工具类
 * @创建时间： 2015年11月24日 上午11:46:50
 * @项目名称： AMapLocationDemo2.x
 * @author hongming.wang
 * @文件名称: AMapUtils.java
 * @类型名称: AMapUtils
 */
object AMapUtils {


    private var sdf: SimpleDateFormat? = null
    /**
     * 根据定位结果返回定位信息的字符串
     * @param location
     * @return
     */
    @Synchronized
    fun getLocationStr(location: AMapLocation?): String? {
        if (null == location) {
            return null
        }
        val sb = StringBuffer()
        //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if (location.errorCode == 0) {
            sb.append("定位成功" + "\n")
            sb.append("定位类型: " + location.locationType + "\n")
            sb.append("经    度    : " + location.longitude + "\n")
            sb.append("纬    度    : " + location.latitude + "\n")
            sb.append("精    度    : " + location.accuracy + "米" + "\n")
            sb.append("提供者    : " + location.provider + "\n")

            sb.append("速    度    : " + location.speed + "米/秒" + "\n")
            sb.append("角    度    : " + location.bearing + "\n")
            // 获取当前提供定位服务的卫星个数
            sb.append("星    数    : " + location.satellites + "\n")
            sb.append("国    家    : " + location.country + "\n")
            sb.append("省            : " + location.province + "\n")
            sb.append("市            : " + location.city + "\n")
            sb.append("城市编码 : " + location.cityCode + "\n")
            sb.append("区            : " + location.district + "\n")
            sb.append("区域 码   : " + location.adCode + "\n")
            sb.append("地    址    : " + location.address + "\n")
            sb.append("兴趣点    : " + location.poiName + "\n")
            //定位完成的时间
            sb.append("定位时间: " + formatUTC(location.time, "yyyy-MM-dd HH:mm:ss") + "\n")
        } else {
            //定位失败
            sb.append("定位失败" + "\n")
            sb.append("错误码:" + location.errorCode + "\n")
            sb.append("错误信息:" + location.errorInfo + "\n")
            sb.append("错误描述:" + location.locationDetail + "\n")
        }
        //定位之后的回调时间
        sb.append("回调时间: " + formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n")
        return sb.toString()
    }

    private fun formatUTC(l: Long, strPattern: String): String {
        var strPattern = strPattern
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss"
        }
        if (sdf == null) {
            try {
                sdf = SimpleDateFormat(strPattern, Locale.CHINA)
            } catch (e: Throwable) {
            }

        } else {
            sdf!!.applyPattern(strPattern)
        }
        return if (sdf == null) "NULL" else sdf!!.format(l)
    }
}
