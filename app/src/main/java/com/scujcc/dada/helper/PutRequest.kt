package com.scujcc.dada.helper

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Created by  范朝波 on 2018/3/5.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
interface PutRequest {

    //更新用户
    @PUT("dada/users/{id}")
    fun updateUsers(@Path("id") id: String, @Body user: User): Call<String>

    @PUT("dada/users/{id}/strokes")
    fun updateStrokes(@Path("id") id: String, @Body stroke: Stroke): Call<String>

    @PUT("dada/users/{id}/likes")
    fun updateLikes(@Path("id") id: String, @Body content: Content): Call<String>

}