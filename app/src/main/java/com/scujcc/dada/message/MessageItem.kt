package com.scujcc.dada.message

import org.litepal.annotation.Column
import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class MessageItem(@Column(unique = true, defaultValue = "unknown")var senderId: String, var imageId: Int, sender: String?, var message: String?, var subImageId: Int, date: String?) : Serializable, DataSupport()