package com.scujcc.dada.main

import android.util.Log
import cn.leancloud.chatkit.LCChatKit
import com.avos.avoscloud.*
import com.avos.avoscloud.im.v2.AVIMClient
import com.avos.avoscloud.im.v2.AVIMException
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback
import com.scujcc.dada.user.User
import org.litepal.LitePal
import org.litepal.LitePalApplication
import org.litepal.crud.DataSupport

/**
 * Created by  范朝波 on 2018/1/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

class DadaApplication : LitePalApplication() {


    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(applicationContext)

        AVOSCloud.setDebugLogEnabled(true)
        LCChatKit.getInstance().init(applicationContext, "7sayB8QAL9XKVIRhqaPvyEgE-gzGzoHsz", "MdN5JWLSqnmR9tPmNnvccosF")
        AVIMClient.setAutoOpen(true)
        PushService.setDefaultPushCallback(this, MainActivity::class.java)

        AVInstallation.getCurrentInstallation().saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {
                if (e == null) {
                    // 保存成功
                    val installationId = AVInstallation.getCurrentInstallation().installationId
                    println("---  " + installationId)
                } else {
                    // 保存失败，输出错误信息
                    println("failed to save installation.")
                }
            }
        })

        loginLC()
    }


    private fun loginLC() {

//        val user = DataSupport.findFirst(User::class.java)
//        LCChatKit.getInstance().open(user.name, object : AVIMClientCallback(){
//            override fun done(p0: AVIMClient?, p1: AVIMException?) {
//                if (p1 == null) {
//                    Log.w("TestLC", "登录成功")
//                }
//            }
//        })
    }
}
