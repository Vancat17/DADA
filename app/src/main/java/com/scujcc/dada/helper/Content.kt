package com.scujcc.dada.helper

import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2018/1/6.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class Content(var id: String, var contentId: String, val image: String, var date: String?, var now: Int?, var total: Int?, var location: String?, var tag: String?, var topic: String?, var price: Double?,val name: String, var content: String, val avatar: String, val score: Int) :Serializable, DataSupport()
