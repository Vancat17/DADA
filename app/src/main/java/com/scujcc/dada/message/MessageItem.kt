package com.scujcc.dada.message

import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class MessageItem(var avatar: String,var name: String?, var message: String?, var image: String,var time: String?) : Serializable, DataSupport()