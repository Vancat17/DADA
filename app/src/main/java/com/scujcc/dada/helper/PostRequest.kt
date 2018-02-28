package com.scujcc.dada.helper

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by  范朝波 on 2018/1/11.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
interface PostRequest {

    @POST("dada/contents")
    fun postContent(@Body content: Content): Call<Content>
}