package com.scujcc.dada.helper

import org.litepal.annotation.Column
import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/30.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class User(@Column(unique = true, defaultValue = "unknown")var userId: String, var image: String?, var name: String, var phone: String, var sex: String?, var level: Int, var verified: Boolean, var sesame: Boolean, var age: String?, var job: String?, var company: String?,var school: String?, var sign: String?, var address: String?) : Serializable, DataSupport()