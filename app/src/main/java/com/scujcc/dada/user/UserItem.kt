package com.scujcc.dada.user

import java.io.Serializable

/**
 * Created by  范朝波 on 2017/12/30.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
class UserItem(var photoId: Int?, var name: String, var sex: Int?, var age: String?, var job: String?, var company: String?, var sign: String?, var vip: Int, var isVerify: Boolean, var isSesame: Boolean) : Serializable {
}