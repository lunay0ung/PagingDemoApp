package com.luna.pagingdemo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUsers(@Query("page") page: Int) : Call<UserResponse>

    @Headers("Authorization: KakaoAK 681e71063792e48eda15ca9c1c0fcb22")
    @GET("/v3/search/book")
    fun searchBooks(
        @Query("page") page: Int,
        //@Query("size") size: Int,
        @Query("query") keyword: String
    ) : Call<BookSearchResponse>
}