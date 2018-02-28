package com.scujcc.dada.helper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by  范朝波 on 2018/1/6.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

interface GetRequest {

    @GET("dada/contents/{id}")
    fun getContent(@Path("id") id: String): Call<Content>

    @GET("dada/contents")
    fun getAllContent(): Call<List<Content>>

    @GET("dada/users/{id}")
    fun getUser(@Path("id") id: String): Call<User>

    @GET("/dada/users/{id}/likes")
    fun getAllLikes(@Path("id") id: String): Call<List<Content>>

    @GET("/dada/users/{id}/strokes")
    fun getAllStrokes(@Path("id") id: String): Call<List<Stroke>>

}