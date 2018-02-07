package com.scujcc.dada.common.cityselector.model

import java.io.Serializable

/**
 * Created by  范朝波 on 2018/1/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class DistrictModel(private val code: String, val name: String, val childs: List<StreetModel>): Serializable