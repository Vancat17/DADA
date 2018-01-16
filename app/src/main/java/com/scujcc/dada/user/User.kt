package com.scujcc.dada.user

import org.litepal.annotation.Column
import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/30.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class User(@Column(unique = true, defaultValue = "unknown")var userId: Long, var photoId: Int?, var name: String, var sex: Int?, var age: String?, var job: String?, var company: String?, var sign: String?, var vip: Int, var isVerify: Boolean, var isSesame: Boolean) : Serializable, DataSupport()