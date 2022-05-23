package com.example.news.repository

import com.example.news.models.News
import com.example.news.models.RoomArticle
import retrofit2.Response

interface MainRepository {
    suspend fun getAllRetrofit():Response<News>

    suspend fun searchNew(string: String): Response<News>

    suspend fun insert(roomArticle: RoomArticle)

    suspend fun getAllRoom(): List<RoomArticle>

    suspend fun delete(roomArticle: RoomArticle)
}