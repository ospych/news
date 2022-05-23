package com.example.news.retrofit

import com.example.news.models.News
import com.example.news.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<News>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<News>
}