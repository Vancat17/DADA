package com.scujcc.dada.content

import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class ContentItem(var imageId: Int, var sender: String?, var topic: String?, var tag: String?, var time: String?, var locaticon: String?, var num: Int, var price: Int, var content: String?, var isLiked: Boolean) : Serializable {

    private val mContentItems: List<ContentItem>? = null
}
