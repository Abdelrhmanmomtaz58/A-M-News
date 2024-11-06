package com.momtaz.amnews

import retrofit2.Call
import retrofit2.http.GET

interface NewsCallable {
    @GET("/v2/top-headlines?country=us&category=general&apiKey=9eab6ad4cf20452096ae810f276c8949&pageSize=100")
    fun getNews():Call<News>
}