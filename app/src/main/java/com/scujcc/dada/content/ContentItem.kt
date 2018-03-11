package com.scujcc.dada.content

import org.litepal.annotation.Column
import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentItem(@Column(unique = true, defaultValue = "unknown") var contentId: String, var image: String, var name: String?, var topic: String?, var tag: String?, var time: String?, var location: String?, val now: Int, var total: Int, var price: Double, var content: String?) : Serializable, DataSupport()