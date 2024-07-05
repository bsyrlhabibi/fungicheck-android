package com.bibi.fungicheck.data.api

import com.bibi.fungicheck.data.response.ArticleResponse
import com.bibi.fungicheck.data.response.LoginResponse
import com.bibi.fungicheck.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServiceGo {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @GET("article/all")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): ArticleResponse
}